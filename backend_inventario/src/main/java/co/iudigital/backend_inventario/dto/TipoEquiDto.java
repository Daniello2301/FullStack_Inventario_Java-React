package co.iudigital.backend_inventario.dto;

import java.time.LocalDate;

public class TipoEquiDto {
    
    private Long id;

    private String nombre;

    private String estado;

    private LocalDate fechaCreacion;

    private LocalDate fechasActualizacion;

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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechasActualizacion() {
        return fechasActualizacion;
    }

    public void setFechasActualizacion(LocalDate fechasActualizacion) {
        this.fechasActualizacion = fechasActualizacion;
    }

    
}
