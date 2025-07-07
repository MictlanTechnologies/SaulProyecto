// Paquete que contiene las entidades relacionadas con los usuarios del sistema.
package model.usuario;

/**Entidad que representa un usuario registrado en NexSit.
 * Hereda el campo `id` de {@link Catalogo}, que se mapea como `id`.
 */
import jakarta.persistence.*;
import lombok.*;
import model.Catalogo;
import vista.acciones.Ejecutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un usuario del sistema.
 * Contiene credenciales y las citas asociadas.
 */
@AttributeOverride(
        name = "id",
        column = @Column(name = "id")
)
@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Usuario extends Catalogo implements Serializable {

    /** Nombre de usuario */
    @ToString.Include
    @Column(name = "usuario", length = 45, nullable = false)
    private String usuario;

    /** Correo electrónico */
    @ToString.Include
    @Column(name = "correo", length = 100, nullable = false)
    private String correo;

    /** Contraseña del usuario */
    @ToString.Include
    @Column(name = "contraseña", length = 255, nullable = false)
    private String contraseña;

    /** Teléfono de contacto */
    @ToString.Include
    @Column(name = "telefono", length = 45, nullable = false)
    private String telefono;

    /** Ubicación o domicilio */
    @ToString.Include
    @Column(name = "ubicacion", length = 100, nullable = false)
    private String ubicacion;

    /** Rol del usuario (cliente o admin) */
    @ToString.Include
    @Column(name = "rol", length = 20, nullable = false)
    private String rol;

    /** Citas asociadas a este usuario */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Citas> citas = new ArrayList<>();

    public static Ejecutable getInstance() {
        return null;
    }
}
