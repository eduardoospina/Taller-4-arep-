package edu.escuelaing.arep;

import edu.escuelaing.arep.Anotaciones.Service;
import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EciSpringBoot {
    private Map<String, Method> servicios = new HashMap<>();
    private File camino;
    private static EciSpringBoot _instance = new EciSpringBoot();

    private EciSpringBoot(){
        String NombredelPackete;
        NombredelPackete = EciSpringBoot.class.getPackage().getName().replace(".","/");
        this.camino = new File("./src/main/java/" + NombredelPackete);
    }

    private EciSpringBoot(File camino) {
        this.camino = camino;
    }


    public static EciSpringBoot getInstance() {
        return _instance;
    }

    public void startServer() {
        loadComponents();
        try {
            HttpServer httpServer = new HttpServer();
            httpServer.start();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadComponents() {
        List<String> componentListSearch = searchComponentList(camino);

        if (componentListSearch != null) {
            componentListSearch.forEach(componentName -> {
                loadServices(componentName);
            });
        }
    }


    private List<String> searchComponentList(File file) {
        List<String> componentes = new ArrayList();
        if (file.isDirectory()){
            for (File raiz : file.listFiles()){
                componentes.addAll(searchComponentList(raiz));
                System.out.println(componentes);
            }
        }
        else{
            if (file.getName().endsWith("java")){
                String path = generarPathCorrecto(file.getPath());
                Class Varc = null;
                try{
                    Varc = Class.forName(path);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if (Varc.isAnnotationPresent(Component.class)){
                    componentes.add(path);
                }
            }
        }
        return componentes;
    }

    private void loadServices(String componentes) {
        try {
            Class Varc = Class.forName(componentes);

            Method[] declaredMethods = Varc.getDeclaredMethods();

            for (Method metodos: declaredMethods) {
                if (metodos.isAnnotationPresent(Service.class)) {
                    Service anotacion = metodos.getAnnotation(Service.class);
                    servicios.put(anotacion.value(), metodos);
                }
                if (metodos.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping anotacion = metodos.getAnnotation(RequestMapping.class);
                    servicios.put(anotacion.value(), metodos);

                }
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String invokeService(String Servicio) {
        try {
            if (!servicios.containsKey(Servicio)) Servicio = "noEncontrado";
            Method serviceMethod = servicios.get(Servicio);
            return (String)serviceMethod.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return "Service error";
    }

    private String generarPathCorrecto(String pathaconvertir){
        return pathaconvertir.replace(".java","").replace("\\",".").replace("..src.main.java.","");
    }

}