package edu.escuelaing.arep.Server.HttpServer;

import edu.escuelaing.arep.Server.HttpServer.EciSpringBoot;

public class CorrerServidor {
    public static void main(String[] args) {
        EciSpringBoot.getInstance().startServer();
    }
}
