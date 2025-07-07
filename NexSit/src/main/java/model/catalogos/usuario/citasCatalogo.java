// Paquete que contiene los catálogos relacionados con gastos (versión consola).
package model.catalogos.usuario;

// Importación de clases necesarias: entidades, repositorios y utilidades.
import model.usuario.Citas;
import model.usuario.Usuario;
import sql.GenericSql;
import sql.hibernateimpl.usuario.CitasHiberImpl;
import sql.hibernateimpl.usuario.UsuarioHiberImpl;
import util.readUtil;
import vista.acciones.gestorCatalogos;

import java.sql.Date;

public class citasCatalogo extends gestorCatalogos<Citas> {

    private static citasCatalogo citasCatalogo;

    private static final GenericSql<Citas> citasHiber = CitasHiberImpl.getInstance();

    private static final GenericSql<Usuario> usuarioHiber = UsuarioHiberImpl.getInstance();


    public static citasCatalogo getInstance() {
        if (citasCatalogo == null) {
            citasCatalogo = new citasCatalogo();
        }
        return citasCatalogo;
    }

    /**
     * Constructor privado que configura el repositorio base.
     */
    private citasCatalogo() {
        super(CitasHiberImpl.getInstance());
    }


    @Override
    public Citas newT() {
        return new Citas();
    }

    @Override
    public boolean processNewT(Citas citas) {
        System.out.print("> Teclee el nombre del artículo: ");
        citas.setArticuloGasto(readUtil.read());

        System.out.print("> Descripción: ");
        citas.setDescripcionGastos(readUtil.read());

        System.out.print("> Monto: ");
        citas.setMontoGastos(readUtil.readBigDecimal());

        System.out.print("> Fecha (yyyy-MM-dd): ");
        citas.setFechaGastos(Date.valueOf(readUtil.read()));

        System.out.print("> Periodo: ");
        citas.setPeriodoGastos(readUtil.read());

        // Asociación con usuario existente
        System.out.print("> ID del usuario que registra el gasto: ");
        Integer idUsuario = readUtil.readInt();
        Usuario usuario = usuarioHiber.findById(idUsuario);
        if (usuario == null) {
            System.out.println("> ✖ No existe ese usuario. No se guardó el gasto.");
            return false;
        }
        citas.setUsuario(usuario);

        // Guardar en base de datos
        citasHiber.save(citas);
        return true;
    }

    @Override
    public boolean processEditT(Citas citas) {
        System.out.print("> Teclee el nuevo nombre del artículo: ");
        citas.setArticuloGasto(readUtil.read());

        System.out.print("> Nueva Descripción: ");
        citas.setDescripcionGastos(readUtil.read());

        System.out.print("> Nuevo Monto: ");
        citas.setMontoGastos(readUtil.readBigDecimal());

        System.out.print("> Nueva Fecha (yyyy-MM-dd): ");
        citas.setFechaGastos(Date.valueOf(readUtil.read()));

        System.out.print("> Nuevo Periodo: ");
        citas.setPeriodoGastos(readUtil.read());

        System.out.print("> ID de usuario (ENTER para dejar igual): ");
        String inp = readUtil.read();
        if (!inp.isBlank()) {
            Usuario u = usuarioHiber.findById(Integer.parseInt(inp));
            if (u == null) {
                System.out.println("> Usuario no existe; se mantiene el actual.");
            } else {
                citas.setUsuario(u);
            }
        }

        citasHiber.update(citas);
        return true;
    }
}

