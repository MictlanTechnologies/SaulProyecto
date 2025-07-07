// Paquete que contiene las implementaciones Hibernate para entidades de gastos
package sql.hibernateimpl.usuario;

import model.usuario.Citas;
import model.usuario.Usuario;
import sql.GenericSql;
import util.HibernateUtil;
import vista.acciones.Ejecutable;
import org.hibernate.Session;

import java.util.List;

public class CitasHiberImpl implements GenericSql<Citas>, Ejecutable {

    private static CitasHiberImpl citasHiber;

    private CitasHiberImpl() {}


    public static CitasHiberImpl getInstance() {
        if (citasHiber == null) {
            citasHiber = new CitasHiberImpl();
        }
        return citasHiber;
    }

    @Override
    public List<Citas> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session
                    .createQuery(
                            "select c from Citas c left join fetch c.usuario",
                            Citas.class)
                    .getResultList();
        }
    }

    @Override
    public boolean save(Citas citas) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(citas);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Citas citas) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(citas);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Citas citas) {
        Session session = HibernateUtil.getSession();
        if (session == null) {
            System.out.println("ERROR DE CONEXIÓN");
            return false;
        }

        session.beginTransaction();
        Citas managed = session.get(Citas.class, citas.getId());
        if (managed != null) {
            session.remove(managed);
        } else {
            System.out.println("> La cita ya no existe en BD");
        }
        session.getTransaction().commit();
        session.close();
        return true;
    }


    public boolean save(Citas citas, Long idUsuario) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            // Referencia al usuario sin hacer SELECT (lazy proxy)
            Usuario usuario = session.getReference(Usuario.class, idUsuario);
            citas.setUsuario(usuario);

            session.persist(citas);
            session.getTransaction().commit();
            return true;
        }
    }

    @Override
    public Citas findById(Integer id) {
        Session session = HibernateUtil.getSession();
        Citas citas = session.get(Citas.class, id);
        session.close();
        return citas;
    }

    /**
     * Implementación vacía del método run() del contrato Ejecutable.
     * Este método puede sobreescribirse si se desea ejecutar lógica específica.
     */
    @Override
    public void run() {
    }

    /**
     * Implementación vacía del método setFlag() del contrato Ejecutable.
     * @param flag bandera para alguna acción condicional
     */
    @Override
    public void setFlag(boolean flag) {
    }
}