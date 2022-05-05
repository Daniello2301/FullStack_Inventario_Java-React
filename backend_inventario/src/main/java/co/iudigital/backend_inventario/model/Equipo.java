package co.iudigital.backend_inventario.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "inventarios")
public class Equipo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Serial Requerido")
    private String serial;

    @NotEmpty(message = "Modelo Requerido")
    private String modelo;

    @NotEmpty(message = "Descripcion Requerida")
    private String descripcion;

    private String imagen;

    @NotEmpty(message = "Precio requerido")
    private Double precio;

    @Column(name = "fecha_compra", nullable = false, insertable = false, updatable = false)
    private Date fechaCompra;

    @OneToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false, referencedColumnName = "usuario_id", updatable = false, insertable = false)
    private Usuario usuario;

    @OneToOne(optional = false)
    @JoinColumn(name = "marca_id", nullable = false, referencedColumnName = "marca_id", updatable = false, insertable = false)
    private Marca marca;

    @OneToOne(optional = false)
    @JoinColumn(name = "tipo_id", nullable = false, referencedColumnName = "tipo_id", updatable = false, insertable = false)
    private TipoEquipo tipo;

    @OneToOne(optional = false)
    @JoinColumn(name = "estado_id", nullable = false, referencedColumnName = "esado_id",updatable = false, insertable = false)
    private EstadoEquipo esatdo;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public TipoEquipo getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipo tipo) {
        this.tipo = tipo;
    }

    public EstadoEquipo getEsatdo() {
        return esatdo;
    }

    public void setEsatdo(EstadoEquipo esatdo) {
        this.esatdo = esatdo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    
}
