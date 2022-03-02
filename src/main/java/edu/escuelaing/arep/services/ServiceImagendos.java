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


@Component
public class ServiceImagendos {
    @RequestMapping("imagendos")
    public static String computeImage() throws URISyntaxException {
        String responseContent;
        String extensionUri = "png";
        URI URIimagen = new URI("/Img/index2.png");

        responseContent = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + constantemapa.get(extensionUri) + "\r\n"
                + "\r\n";

        File file = new File("src/main/resources/" + URIimagen.getPath());
        try {
            BufferedImage bi = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(HttpServer.outputStream);
            ImageIO.write(bi, extensionUri, byteArrayOutputStream);
            dataOutputStream.writeBytes(responseContent);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseContent;
    }
}
