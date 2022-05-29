package co.iudigital.backend_inventario.dto;


import java.time.LocalDateTime;

import co.iudigital.backend_inventario.model.Usuario;

public class TipoEquiDto {
    
    private Long id;

    private String nombre;

    private String estado;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private LocalDateTime fechasActualizacion;

    private Usuario usuario;

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

    public LocalDateTime getFechaActualizacion() {
        return fechasActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechasActualizacion) {
        this.fechasActualizacion = fechasActualizacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    

    
}
