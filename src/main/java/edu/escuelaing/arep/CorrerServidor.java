package edu.escuelaing.arep;


/**
 * Clae que inicia el proceso del proyecto
 * @author Eduardo ospina
 */
public class CorrerServidor {

    /**
     * metodo que manda a iniciar el framework diseñado.
     * @param args string[] necesitado para utilizar el main
     */
    public static void main(String[] args) {

        EciSpringBoot.getInstance().startServer();
    }
}
