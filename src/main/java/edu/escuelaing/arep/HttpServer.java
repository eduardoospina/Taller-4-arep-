package edu.escuelaing.arep;



import edu.escuelaing.arep.EciSpringBoot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HttpServer {

    public final static Map<String, String> constantemapa = new HashMap<String, String>();
    public static OutputStream outputStream;
    private URI UriaUse;


    public void start() throws IOException, URISyntaxException {



        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println();
            System.exit(1);
        }



        boolean running = true;



        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }


            outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            ArrayList<String> request = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                request.add(inputLine);

                if (!in.ready()) {
                    break;
                }
            }

            String file;
            System.out.println(request.get(0));
            file = request.get(0).split(" ")[1];

            UriaUse = new URI(file);


            if (file.startsWith("/Apps/")) {
                outputLine = invokeService(file.replace("/Apps/", ""));
            }
            else if (file.startsWith("/Img/")) {
                outputLine = invokeService(file.replace("/Img/", ""));
            }
            else if (file.length() == 1) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>\n"
                        + "</head>"
                        + "<body>"
                        + "<h1>Pagina de inicio</h1>"
                        + "<h3>Servidor Web en Java, entregar paginas html e imagenes png</h3>"
                        + "<div></div>"
                        + "<h3>Links de uso: </h3>"
                        + "<h3> /Apps/Estado </h3>"
                        + "<h3> /Apps/fecha </h3>"
                        + "<h3> /Img/imagenuno </h3>"
                        + "<h3> /Img/imagendos </h3>"
                        + "<h3> /MiniSpring </h3>"
                        + "</body>"
                        + "</html>";
            }
            else {
                String[] controller = file.split("/");
                outputLine = invokeService(controller[1]);
            }
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }



    private String invokeService(String service) {

        return EciSpringBoot.getInstance().invokeService(service);
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }

    public HttpServer() {
        setTypes();
    }

    private void setTypes() {
        for (String[] type : TIPOS) {
            constantemapa.put(type[0], type[1]);
        }
    }

    public final static String[][] TIPOS = {
            {"html", "text/html"},
            {"jpg", "image/jpg"},
            {"js", "text/javascript"},
            {"jpeg", "image/jpeg"},
            {"png", "image/png"}};



}
