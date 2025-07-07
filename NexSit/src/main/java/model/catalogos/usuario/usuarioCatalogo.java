// Paquete que contiene los catálogos relacionados con usuarios (versión consola).
package model.catalogos.usuario;

// Importación de clases necesarias: entidades, repositorios y utilidades.
import model.usuario.Usuario;
import sql.GenericSql;
import sql.hibernateimpl.usuario.UsuarioHiberImpl;
import util.readUtil;
import vista.acciones.gestorCatalogos;

/**
 * Catálogo para la gestión de usuarios desde consola.
 * Permite registrar y editar entidades {@link Usuario}.
 * Extiende {gestorCatalogos} y aplica patrón Singleton.
 */
public class usuarioCatalogo extends gestorCatalogos<Usuario> {

    /** Instancia única del catálogo (singleton) */
    private static usuarioCatalogo usuarioCatalogo;

    /** Repositorio Hibernate para usuarios */
    private static final GenericSql<Usuario> usuarioHiber = UsuarioHiberImpl.getInstance();

    /**
     * Devuelve la única instancia del catálogo.
     * devuelve objeto {@code usuarioCatalogo}
     */
    public static usuarioCatalogo getInstance() {
        if (usuarioCatalogo == null) {
            usuarioCatalogo = new usuarioCatalogo();
        }
        return usuarioCatalogo;
    }


    private usuarioCatalogo() {
        super(UsuarioHiberImpl.getInstance());
    }


    @Override
    public Usuario newT() {
        return new Usuario();
    }


    @Override
    public boolean processNewT(Usuario usuario) {
        System.out.print("> Teclee su nombre de Perfil: ");
        usuario.setPerfilUsuario(readUtil.read());

        System.out.print("> Teclee su contraseña de Perfil: ");
        usuario.setContraseñaUsuario(readUtil.read());

        usuarioHiber.save(usuario);
        return true;
    }

    @Override
    public boolean processEditT(Usuario usuario) {
        System.out.print("> Teclee su nuevo nombre de Perfil: ");
        usuario.setPerfilUsuario(readUtil.read());

        System.out.print("> Teclee su nueva contraseña de Perfil: ");
        usuario.setContraseñaUsuario(readUtil.read());

        usuarioHiber.update(usuario);
        return true;
    }
}
