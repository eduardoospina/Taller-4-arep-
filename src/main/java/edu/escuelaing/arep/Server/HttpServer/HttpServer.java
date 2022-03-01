package edu.escuelaing.arep.Server.HttpServer;



import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HttpServer {

    public final static Map<String, String> constantemapa = new HashMap<String, String>();

    public static void start() throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
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

            String file = "";

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine;
            ArrayList<String> request = new ArrayList<String>();
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                request.add(inputLine);

                if (!in.ready()) {
                    break;
                }
            }

            URI URIdelRecurso;

            file = request.get(0).split(" ")[1];

            try {
                URIdelRecurso = new URI(file);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            if (file.startsWith("/Apps/")) {
                outputLine = invokeService(file.replace("/Apps/", ""));
            }
            else if (file.startsWith("/Img/")) {
                outputLine = invokeService(file.replace("/img/", ""));
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
                        + "Pagina default"
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



    private static String invokeService(String service) {

        return EciSpringBoot.getInstance().invokeService(service);
    }

    private static boolean Imagenbool(String Uri) {
        return Uri.equals("png") || Uri.equals("jpg") || Uri.equals("jpge");
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
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
