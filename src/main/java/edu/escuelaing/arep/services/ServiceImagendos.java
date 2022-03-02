package edu.escuelaing.arep.services;

import edu.escuelaing.arep.Anotaciones.Component;
import edu.escuelaing.arep.Anotaciones.RequestMapping;
import edu.escuelaing.arep.server.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static edu.escuelaing.arep.server.HttpServer.constantemapa;


/**
 * clase que maneja los servicios de imagen dos
 * @author eduardo ospina
 */
@Component
public class ServiceImagendos {

    /**
     * metodo que maneja el retorno de una imagen
     * @return String, imagen que se guarda
     */
    @RequestMapping("imagendos")
    public static String Imagenobtener() throws URISyntaxException {
        String imagencontenido;
        String URIImagen = "png";
        URI URIimagen = new URI("/Img/index2.png");

        imagencontenido = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + constantemapa.get(URIImagen) + "\r\n"
                + "\r\n";

        File file = new File("src/main/resources/" + URIimagen.getPath());
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(HttpServer.outputStream);
            ImageIO.write(bi, URIImagen, byteArrayOutputStream);
            dataOutputStream.writeBytes(imagencontenido);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagencontenido;
    }
}
