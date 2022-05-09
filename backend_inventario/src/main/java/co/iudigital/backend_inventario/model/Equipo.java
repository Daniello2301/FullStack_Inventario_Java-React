package co.iudigital.backend_inventario.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipos")
public class Equipo implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Serial requerido")
    private String serial;

    @NotNull(message = "Modelo requerido")
    private String modelo;

    @NotNull(message = "Descripcion requerida")
    private String descripcion;

    @NotNull
    private String foto;

    @NotNull
    private Double precio;

    @Column(name = "fecha_compra")
    private LocalDate fechaCompra;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioId;

    @OneToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marcaId;

    @OneToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoEquipo tipoId;

    @OneToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private EstadoEquipo estadoId;

    @NotNull
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @NotNull
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Marca getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Marca marcaId) {
        this.marcaId = marcaId;
    }

    public TipoEquipo getTipoId() {
        return tipoId;
    }

    public void setTipoId(TipoEquipo tipoId) {
        this.tipoId = tipoId;
    }

    public EstadoEquipo getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(EstadoEquipo estadoId) {
        this.estadoId = estadoId;
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


