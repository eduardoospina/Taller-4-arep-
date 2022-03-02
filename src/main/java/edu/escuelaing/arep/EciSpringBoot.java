package edu.escuelaing.arep;

import edu.escuelaing.arep.Anotaciones.Service;
import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;
import edu.escuelaing.arep.server.HttpServer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Clase de EciSpringboot que genera las variable necesarias para el amnejo y contine los metodos
 * @author Eduardo Ospina
 */
public class EciSpringBoot {
    private Map<String, Method> servicios = new HashMap<>();
    private File camino;
    private static EciSpringBoot _instance = new EciSpringBoot();

    /**
     * Constructor de la clase, en donde se asigna el path para la busqueda de las anotaciones.
     */
    private EciSpringBoot(){
        String NombredelPackete;
        NombredelPackete = EciSpringBoot.class.getPackage().getName().replace(".","/");
        this.camino = new File("./src/main/java/" + NombredelPackete);
    }

    /**
     * metodo que retorna la instancia
     * @return -instance EciSpringBoot
     */
    public static EciSpringBoot getInstance() {
        return _instance;
    }


    /**
     * metodo que corre el metodo del servidor.
     */
    public void startServer() {
        loadComponents();
        try {
            HttpServer httpServer = new HttpServer();
            httpServer.start();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo que craga todos los componentes con el @Component en el proyecto
     */
    private void loadComponents() {
        List<String> componentListSearch = searchComponentList(camino);

        if (componentListSearch != null) {
            componentListSearch.forEach(componentName -> {
                loadServices(componentName);
            });
        }
    }


    /**
     * metodo que busca en un camino especifico todos los @components
     * @param file de tipo File
     * @return List<String> de los componentes existentes en un camino expecifico
     */    private List<String> searchComponentList(File file) {
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

    /**
     * metodo que guarda los metodos dentro del hashmap.
     * @param componentes de tipo String, nombre de componente
     */
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

    /**
     * metodo para invocar un metodo de una clase
     * @param Servicio de tipo string que se quiere invocar
     * @return String, representando el metodo a ejecutar
     */
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

    /**
     * metodo que tranforma al format classpath.
     * @param pathaconvertir de tipo string, representando el path que se quiere convertir
     * @return Strin, en format claspath
     */
    private String generarPathCorrecto(String pathaconvertir){
        return pathaconvertir.replace(".java","").replace("\\",".").replace("..src.main.java.","");
    }

}