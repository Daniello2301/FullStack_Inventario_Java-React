package co.iudigital.backend_inventario.converter;

import org.springframework.stereotype.Component;

import co.iudigital.backend_inventario.dto.MarcaDto;
import co.iudigital.backend_inventario.model.Marca;

@Component
public class MarcaConverter {
    
    public Marca marcaDTOToMarca( MarcaDto marcaDTO ){
        
        Marca marca = new Marca();

        marca.setId(marcaDTO.getId());
        marca.setNombre(marcaDTO.getNombre());
        marca.setEstado(marcaDTO.getEstado());
        marca.setFechaCreacion(marcaDTO.getFechaCreacion());
        marca.setFechaActualizacion(marcaDTO.getFechaActualizacion());
        marca.setUsuario(marcaDTO.getUsuario());

        return marca;

    }

    public MarcaDto marcaToMarcaDTO( Marca marca ){
        
        MarcaDto marcaDTO = new MarcaDto();

        marcaDTO.setId(marca.getId());
        marcaDTO.setNombre(marca.getNombre());
        marcaDTO.setEstado(marca.getEstado());
        marcaDTO.setFechaCreacion(marca.getFechaCreacion());
        marcaDTO.setFechaActualizacion(marca.getFechaActualizacion());
        marcaDTO.setUsuario(marca.getUsuario());

        return marcaDTO;

    }
}
