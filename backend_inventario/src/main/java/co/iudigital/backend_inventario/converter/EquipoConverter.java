package co.iudigital.backend_inventario.converter;

import org.springframework.stereotype.Component;

import co.iudigital.backend_inventario.dto.EquipoDto;
import co.iudigital.backend_inventario.model.Equipo;

@Component
public class EquipoConverter {

    public Equipo EquipoDTOToEquipo(EquipoDto equipoDTO){

        Equipo equipo = new Equipo();

        equipo.setId(equipoDTO.getId());
        equipo.setSerial(equipoDTO.getSerial());
        equipo.setModelo(equipoDTO.getModelo());
        equipo.setDescripcion(equipoDTO.getDescripcion());
        equipo.setFoto(equipoDTO.getImagen());
        equipo.setPrecio(equipoDTO.getPrecio());
        equipo.setEstado(equipoDTO.getEstado());
        equipo.setFechaCompra(equipoDTO.getFechaCompra());
        equipo.setUsuario(equipoDTO.getUsuario());
        equipo.setMarca(equipoDTO.getMarca());
        equipo.setTipoEquipo(equipoDTO.getTipoEquipo());
        equipo.setEstadoEquipo(equipoDTO.getEstadoEquipo());
        equipo.setFechaCreacion(equipoDTO.getFechaCreacion());
        equipo.setFechaActualizacion(equipoDTO.getFechaActualizacion());

        return equipo;

    }

    public EquipoDto EquipoToEquipoDTO(Equipo equipo){

        EquipoDto equipoDTO = new EquipoDto();

        equipoDTO.setId(equipo.getId());
        equipoDTO.setSerial(equipo.getSerial());
        equipoDTO.setModelo(equipo.getModelo());
        equipoDTO.setDescripcion(equipo.getDescripcion());
        equipoDTO.setImagen(equipo.getFoto());
        equipoDTO.setPrecio(equipo.getPrecio());
        equipoDTO.setEstado(equipo.getEstado());
        equipoDTO.setFechaCompra(equipo.getFechaCompra());
        equipoDTO.setUsuario(equipo.getUsuario());
        equipoDTO.setMarca(equipo.getMarca());
        equipoDTO.setTipoEquipo(equipo.getTipoEquipo());
        equipoDTO.setEstadoEquipo(equipo.getEstadoEquipo());
        equipoDTO.setFechaCreacion(equipo.getFechaCreacion());
        equipoDTO.setFechaActualizacion(equipo.getFechaActualizacion());

        return equipoDTO;

    }
    
}
