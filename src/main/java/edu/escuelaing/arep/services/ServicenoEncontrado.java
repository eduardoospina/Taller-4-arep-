package edu.escuelaing.arep.services;

import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;


/**
 * clase que maneja los servicios de la pagina no valida
 * @author eduardo ospina
 */
@Component
public class ServicenoEncontrado {

    /**
     * metodo que retorna la pagina html cuando la url no es valida
     * @return String, html que se pide
     */
    @RequestMapping("noEncontrado")
    public static String noEncontrado() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Pagina de error 404</title>\n"
                + "</head>"
                + "<body>"
                + "<div>"
                + "<h1>404 Error</h1>"
                + "</div>"
                + "<h2>La pagina no se encuentra, se genera el error 404. Se utiliza para cuando se intente a ingresar un url no valido </h2>"
                + "<p>"
                + "No se encuentra la pagina que se esta buscando, no existe el url buscado"
                + "</p>"
                + "</body>"
                + "</html>";
    }
}
