package co.iudigital.backend_inventario.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.model.TipoEquipo;
import co.iudigital.backend_inventario.model.Usuario;
import co.iudigital.backend_inventario.repository.IUsuarioRepository;

public class TipoEquipoConverter {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    
    public TipoEquipo TipoEquipoDtoToTipoEquipo(TipoEquiDto tipoEquiDto) {

        TipoEquipo tipoEquipo = new TipoEquipo();

        tipoEquipo.setId(tipoEquiDto.getId());
        tipoEquipo.setNombre(tipoEquiDto.getNombre());
        tipoEquipo.setEstado(tipoEquiDto.getEstado());
        tipoEquipo.setFechaCreacion(tipoEquiDto.getFechaCreacion());
        tipoEquipo.setFechaActualizacion(tipoEquiDto.getFechasActualizacion());

        Optional<Usuario> usuario = usuarioRepository.findById(tipoEquiDto.getUsuarioId());
        if(!usuario.isPresent()){ return null; }
        tipoEquipo.setUsuario(usuario.get());

        return tipoEquipo; 
    }


    public TipoEquiDto TipoEquipoToTipoEquiDto(TipoEquipo tipoEquipo){

        TipoEquiDto tipoEquiDto = new TipoEquiDto();

        tipoEquiDto.setId(tipoEquipo.getId());
        tipoEquiDto.setNombre(tipoEquipo.getNombre());
        tipoEquiDto.setEstado(tipoEquipo.getEstado());
        tipoEquiDto.setFechaCreacion(tipoEquipo.getFechaCreacion());
        tipoEquiDto.setFechasActualizacion(tipoEquipo.getFechaActualizacion());
        Usuario usuario = usuarioRepository.findById(tipoEquiDto.getUsuarioId());
        if(!usuario.isPresent()){ return null; }

        tipoEquiDto.setUsuarioId(usuario.get()));

        return tipoEquiDto;
    }
}
