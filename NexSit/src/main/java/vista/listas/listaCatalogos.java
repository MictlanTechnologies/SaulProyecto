// Paquete que contiene la lista de menús disponibles para NexSit
package vista.listas;

import vista.acciones.Ejecutable;
import vista.acciones.Menu;
import vista.acciones.leerAcciones;

/**
 * Menú principal de acceso a catálogos del sistema NexSit.
 * Desde aquí el usuario puede acceder al menú de usuarios o a las funciones adicionales.
 * Implementa patrón Singleton.
 */
public class listaCatalogos extends leerAcciones {

    // Instancia única del menú de catálogos
    public static listaCatalogos listaCatalogos;

    /** Constructor privado para implementar Singleton */
    private listaCatalogos() {
    }

    /**
     * Devuelve la instancia única del menú de catálogos.
     * devuelve instancia de listaCatalogos
     */
    public static listaCatalogos getInstance() {
        if (listaCatalogos == null) {
            listaCatalogos = new listaCatalogos();
        }
        return listaCatalogos;
    }

    /**
     * Muestra las opciones disponibles en el menú NexSit.
     */
    @Override
    public void despliegaMenu() {
        System.out.println("\n\t::: Menu NexSit :::");
        System.out.println("\t> Selecciona una opción:");
        System.out.println("1.- Usuario");
        System.out.println("2.- Salir");
        Menu.seleccionaOpcion();
    }

    /**
     * Límite inferior de opciones permitidas.
     * devuelve 1
     */
    @Override
    public int valorMinMenu() {
        return 1;
    }

    /**
     * Límite superior de opciones permitidas.
     * devuelve 3
     */
    @Override
    public int valorMaxMenu() {
        return 2;
    }

    /**
     * Ejecuta la acción correspondiente a la opción seleccionada.
     */
    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;
        switch (opcion) {
            case 1:
                ejecutable = listaUsuario.getInstance(); // Submenú de gestión de usuarios
                break;
            case 2:
                flag = false; // Salir del menú
                break;
            default:
                Menu.opcionInvalida(); // Manejo de error
                break;
        }
        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
