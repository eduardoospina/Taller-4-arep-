package edu.escuelaing.arep.services;

import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;


/**
 * CLase de servicios de prueba MiniSpring
 * @author eduardo ospina
 */
@Component
public class MiniSpring {

    /**
     * metodo que returna la pagina html de prueba.
     * @return String en formato html
     */
    @RequestMapping("MiniSpring")
    public static String index() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>MiniSpringTest</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>Prueba MiniSpring </h1>"
                + "</div>"
                + "<p>"
                + "Greetings from Spring Boot!"
                + "</p>"
                + "<h3> Primera pagina miniprueba spring en Java.</h3>"
                + "</body>"
                + "</html>";
    }
}
