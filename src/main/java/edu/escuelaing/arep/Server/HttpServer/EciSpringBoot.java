package edu.escuelaing.arep.Server.HttpServer;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EciSpringBoot {
    private Map<String, Method> services = new HashMap<>();

    private File camino;

    private static EciSpringBoot instancia = new EciSpringBoot();

    private EciSpringBoot(){
        String NombredelPackete;
        NombredelPackete = EciSpringBoot.class.getPackage().getName().replace(".", "/");
        //DEFAULT_PATH + NombredelPackete
        this.camino = new File("./src/main/java/" + NombredelPackete);
    }


    public static EciSpringBoot getInstance() {

        return instancia;
    }

    public void startServer() {
        loadComponents();
        try {
            HttpServer httpServer = new HttpServer();
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadComponents() {
        List<String> componentList = searchComponentList(camino);

        for(String t : componentList) {
            loadServices(t);
        }
    }


    private List<String> searchComponentList(File file) {
        List<String> componentes = new ArrayList();
        if (file.isDirectory()){
            for (File raiz : file.listFiles()){
                componentes.addAll(searchComponentList(raiz));
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

            for (Method metodos: Varc.getDeclaredMethods()) {
                if (metodos.isAnnotationPresent(Service.class)) {
                    Service anotacion = metodos.getAnnotation(Service.class);
                    services.put(anotacion.value(), metodos);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String invokeService(String serviceName) {
        try {
            if (!services.containsKey(serviceName)) serviceName = "notFound";
            Method serviceMethod = services.get(serviceName);
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