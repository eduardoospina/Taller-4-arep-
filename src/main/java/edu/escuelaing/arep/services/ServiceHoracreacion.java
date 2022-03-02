package edu.escuelaing.arep.services;

import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;


@Component
public class ServiceHoracreacion {
    @RequestMapping("fecha")
    public static String date() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Pagina de Fecha Actual</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>La Fecha actual es, utilizando java.util.date: </h1>"
                + "</div>"
                + "<p>"
                + "Fecha de creacion de la pagina: 01/03/2022 A las 11:22 pm. prueba de acc4esso a diferentes HTML"
                + "</p>"
                + "</body>"
                + "</html>";
    }
}
