// Paquete que contiene las entidades relacionadas con las citas.
package model.usuario;

// Importaciones necesarias para la persistencia, uso de Lombok y colecciones.
import jakarta.persistence.*;
import lombok.*;
import model.Catalogo;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

/**
 * Entidad que representa una cita registrada en el sistema.
 * Se almacena en la tabla `citas` y hereda de {@link Catalogo}.
 */
@AttributeOverride(
        name = "id",
        column = @Column(name = "id_cita")
)
@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Citas extends Catalogo implements Serializable {
    /**
     * Devuelve el ID del usuario relacionado a la cita.
     */
    @ToString.Include(name = "idUsuario")
    public Integer getIdUsuario() {
        return usuario != null ? usuario.getId() : null;
    }

    /** Día de la cita */
    @ToString.Include
    @Column(name = "dia", nullable = false)
    private Date dia;

    /** Hora de la cita */
    @ToString.Include
    @Column(name = "hora", nullable = false)
    private Time hora;

    /** Estado actual de la cita */
    @ToString.Include
    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    /** Relación muchos-a-uno con Usuario */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @ToString.Exclude
    private Usuario usuario;

    /**
     * Muestra el gasto en formato resumen (usado en listados).
     * Muestra la cita en formato resumen.
     */
    @Override
    public String toString() {
        return dia + " " + hora + " (" + estado + ")";
    }
}
