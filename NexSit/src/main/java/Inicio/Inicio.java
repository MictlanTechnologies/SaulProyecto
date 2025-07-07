package Inicio;

import vista.acciones.seleccionEjecutable;


public class Inicio {

    public static void main(String[] args) {
        System.out.println("\t:: NexSit ::"); // Mensaje de bienvenida
        seleccionEjecutable.getInstance().run(); // Inicia la lógica de selección de acción
        System.out.println("\t¡Hasta pronto!");  // Mensaje de salida
    }
}
