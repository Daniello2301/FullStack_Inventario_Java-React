package co.iudigital.backend_inventario.converter;

import org.springframework.stereotype.Component;

import co.iudigital.backend_inventario.dto.EstadoEquiDto;
import co.iudigital.backend_inventario.model.EstadoEquipo;

@Component
public class EstadoEquipoConverter {
    
    public EstadoEquipo EstadoEquipoDTOToEstadoEquipo(EstadoEquiDto estadoDTO){
       
        EstadoEquipo estado = new EstadoEquipo();

        estado.setId(estadoDTO.getId());
        estado.setNombre(estadoDTO.getNombre());
        estado.setEstado(estadoDTO.getEstado());
        estado.setFechaCreacion(estadoDTO.getFechaCreacion());
        estado.setFechaActualizacion(estadoDTO.getFechaActualizacion());
        estado.setUsuario(estadoDTO.getUsuario());

        return estado;
    }

    public EstadoEquiDto EstadoEquipoToEstadoEquipoDTO(EstadoEquipo estado){
       
        EstadoEquiDto estadoDTO = new EstadoEquiDto();

        estadoDTO.setId(estado.getId());
        estadoDTO.setNombre(estado.getNombre());
        estadoDTO.setEstado(estado.getEstado());
        estadoDTO.setFechaCreacion(estado.getFechaCreacion());
        estadoDTO.setFechaActualizacion(estado.getFechaActualizacion());
        estadoDTO.setUsuario(estado.getUsuario());

        return estadoDTO;
    }
}
