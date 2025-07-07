// Paquete del sistema que contiene los menús relacionados a la gestión de usuarios
package vista.listas;

import model.catalogos.usuario.citasCatalogo;
import model.catalogos.usuario.usuarioCatalogo;
import vista.acciones.Ejecutable;
import vista.acciones.Menu;
import vista.acciones.leerAcciones;


/**
 * Submenú del sistema para la gestión de entidades relacionadas al usuario:
 * Usuario, Persona, Domicilio y Contacto.
 * Implementa el patrón Singleton para mantener una única instancia del menú.
 */
public class listaUsuario extends leerAcciones {

    /** Instancia única de listaUsuario (Singleton) */
    private static listaUsuario listaUsuario;

    /** Constructor privado para restringir instanciación externa */
    private listaUsuario() {}

    /**
     * Devuelve la instancia única de listaUsuario.
     * regresa instancia única de listaUsuario
     */
    public static listaUsuario getInstance() {
        if (listaUsuario == null) {
            listaUsuario = new listaUsuario();
        }
        return listaUsuario;
    }

    /**
     * Despliega el submenú de opciones relacionadas con entidades de usuario.
     */
    @Override
    public void despliegaMenu() {
        System.out.println("\n\t::: Catálogo de Usuario :::");
        System.out.println("1.- Usuario");
        System.out.println("2.- Citas");
        System.out.println("3.- Salir ");
        Menu.seleccionaOpcion();
    }


    @Override
    public int valorMinMenu() {
        return 1;
    }


    @Override
    public int valorMaxMenu() {
        return 3;
    }

    @Override
    public void procesaOpcion() {
        Ejecutable ejecutable = null;

        switch (opcion) {
            case 1:
                ejecutable = usuarioCatalogo.getInstance();   // Gestión de Usuario
                break;
            case 2:
                ejecutable = citasCatalogo.getInstance();   // Gestión de Persona
                break;
            case 3:
                flag = false; // Salir del menú
                break;
            default:
                Menu.opcionInvalida(); // Entrada inválida
                break;
        }

        // Ejecutar el módulo correspondiente si fue seleccionado
        if (ejecutable != null) {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
