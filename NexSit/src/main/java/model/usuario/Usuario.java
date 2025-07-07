// Paquete que contiene las entidades relacionadas con los usuarios del sistema.
package model.usuario;

/**
 * Entidad que representa un usuario del sistema ECONOMIX.
 * Contiene credenciales y relaciones con personas y gastos asociados.
 * Hereda el campo `id` de {@link Catalogo}, que se mapea como `idUsuario`.
 */

import jakarta.persistence.*;
import lombok.*;
import model.Catalogo;
import vista.acciones.Ejecutable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un usuario del sistema ECONOMIX.
 * Contiene credenciales y relaciones con personas y gastos asociados.
 * Hereda el campo `id` de {@link Catalogo}, que se mapea como `idUsuario`.
 */
@AttributeOverride(
        name = "id",
        column = @Column(name = "idUsuario")
)
@Entity
@Table(name = "tbl_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Usuario extends Catalogo implements Serializable {

    /** Nombre de usuario o identificador de perfil */
    @ToString.Include
    private String perfilUsuario;

    /** Contraseña del usuario (se recomienda enmascarar o cifrar en aplicaciones reales) */
    @ToString.Include
    private String contraseñaUsuario;

    /** Lista de gastos registrados por este usuario */
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Citas> citas = new ArrayList<>();

    public static Ejecutable getInstance() {
        return null;
    }
}
