package co.iudigital.backend_inventario.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventarios")
public class Equipo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Serial Requerido")
    private String serial;

    @NotEmpty(messege = "Modelo Requerido")
    private String modelo;

    @NotEmpty(messege = "Descripcion Requerida")
    private String descripcion;

    private String imagen;

    @NotEmpty(messege = "Precio requerido")
    private Double precio;

    @NotEmpty(message = "Fecha de compra requerida ")
    @Column(name = "fecha_compra")
    private Date fechaCompra;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @NotEmpty(messege = "Usuario requerido")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @OneToOne
    @JoinColumn(name = "tipo_id")
    private TipoEquipo tipo;

    @OneToOne
    @JoinColumn(name = "estado_id")
    private EstadoEquipo esatdo;

    @Column(name = "fecha_compra")
    @NotEmpty(message = "Fecha de creacion requerida")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
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
