package co.iudigital.backend_inventario.converter;

import org.springframework.stereotype.Component;

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.model.TipoEquipo;

@Component
public class TipoEquipoConverter {
    
    public TipoEquipo tipoEquipoDTOToTipoEquipo(TipoEquiDto tipoDTO){

        TipoEquipo tipoEquipo = new TipoEquipo();

        tipoEquipo.setId(tipoDTO.getId());
        tipoEquipo.setNombre(tipoDTO.getNombre());
        tipoEquipo.setEstado(tipoDTO.getEstado());
        tipoEquipo.setFechaCreacion(tipoDTO.getFechaCreacion());
        tipoEquipo.setFechaActualizacion(tipoDTO.getFechaActualizacion());
        tipoEquipo.setUsuario(tipoDTO.getUsuario());

        return tipoEquipo;
    }

    public TipoEquiDto tipoEquipoToTipoEquipoDTO(TipoEquipo tipoEquipo){

        TipoEquiDto tipoEquipoDTO = new TipoEquiDto();

        tipoEquipoDTO.setId(tipoEquipo.getId());
        tipoEquipoDTO.setNombre(tipoEquipo.getNombre());
        tipoEquipoDTO.setEstado(tipoEquipo.getEstado());
        tipoEquipoDTO.setFechaCreacion(tipoEquipo.getFechaCreacion());
        tipoEquipoDTO.setFechaActualizacion(tipoEquipo.getFechaActualizacion());
        tipoEquipoDTO.setUsuario(tipoEquipo.getUsuario());


        return tipoEquipoDTO;
    }
}
