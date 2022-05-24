package co.iudigital.backend_inventario.dto;


import java.time.LocalDateTime;

public class TipoEquiDto {
    
    private Long id;

    private String nombre;

    private String estado;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private LocalDateTime fechasActualizacion = LocalDateTime.now();

    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechasActualizacion() {
        return fechasActualizacion;
    }

    public void setFechasActualizacion(LocalDateTime fechasActualizacion) {
        this.fechasActualizacion = fechasActualizacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    
}
