package edu.escuelaing.arep.Ejercicios;

public class Pruebas {
    public void m1(){}

    @Test
    public static void m2(){
        throw new RuntimeException("Error m2");
    }

    @Test
    public static void m3(){}

    @Test
    public static void m4(){
        throw new RuntimeException("Error m4");
    }

    public void m5(){}
}
