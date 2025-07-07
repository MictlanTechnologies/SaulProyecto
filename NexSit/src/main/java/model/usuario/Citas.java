// Paquete que contiene entidades relacionadas con gastos.
package model.usuario;

// Importaciones necesarias para la persistencia, uso de Lombok y colecciones.
import jakarta.persistence.*;
import lombok.*;
import model.Catalogo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * Entidad que representa un gasto realizado por un usuario.
 * Se almacena en la tabla `tbl_gastos` y hereda de {@link Catalogo}.
 */
@AttributeOverride(
        name = "id",
        column = @Column(name = "idGastos")  // Renombrar campo heredado
)
@Entity
@Table(name = "tbl_gastos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Citas extends Catalogo implements Serializable {

    /**
     * Devuelve el ID del usuario que registró el gasto.
     */
    @ToString.Include(name = "idUsuario")
    public Integer getIdUsuario() {
        return usuario != null ? usuario.getId() : null;
    }

    /** Nombre del artículo adquirido */
    @ToString.Include
    @Column(name = "artículoGasto", length = 100)
    private String articuloGasto;

    /** Descripción del gasto */
    @ToString.Include
    @Column(name = "descripciónGasto", columnDefinition = "TEXT")
    private String descripcionGastos;

    /** Monto gastado */
    @ToString.Include
    @Column(name = "montoGasto", precision = 10, scale = 2)
    private BigDecimal montoGastos;

    /** Fecha en la que se realizó el gasto */
    @ToString.Include
    @Column(name = "fechaGastos")
    private Date fechaGastos;

    /** Periodo asociado al gasto (quincenal, mensual, etc.) */
    @ToString.Include
    @Column(name = "periodoGastos", length = 50)
    private String periodoGastos;

    /** Relación muchos-a-uno con Usuario (quién registró el gasto) */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUsuario", nullable = false)
    @ToString.Exclude
    private Usuario usuario;

    /**
     * Muestra el gasto en formato resumen (usado en listados).
     */
    @Override
    public String toString() {
        return articuloGasto + " " + montoGastos + " ";
    }
}
