package edu.escuelaing.arep.services;

import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;


@Component
public class ServiceStatus{
    @RequestMapping("Estado")
    public static String status() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Pagina de estado</title>\n"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<h1>Estado: prueba de funcionamiento path.</h1>"
                + "</div>"
                + "<p>"
                + " El estado de la aplicacion es funcional, el numero de la suerte es 4567, hora 1:03 AM"
                + "</p>"
                + "</body>"
                + "</html>";
    }



}