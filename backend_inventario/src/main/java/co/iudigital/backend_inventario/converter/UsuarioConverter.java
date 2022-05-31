package co.iudigital.backend_inventario.converter;

import org.springframework.stereotype.Component;

import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.model.Usuario;

@Component
public class UsuarioConverter {
    

    public UsuarioDto usuarioToUsuarioDTO(Usuario usuario){

        UsuarioDto usuarioDTO = new UsuarioDto();
        
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setContrasena(usuario.getContrasena());
        usuarioDTO.setEstado(usuario.getEstado());
        usuarioDTO.setFechaCreacion(usuario.getFechaCreacion());
        usuarioDTO.setFechaActualizacion(usuario.getFechaActualizacion());

        return usuarioDTO;
    }


    public Usuario usuarioDTOToUsuario(UsuarioDto usuarioDto){

        Usuario usuario = new Usuario();

        usuario.setId(usuarioDto.getId());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setContrasena(usuarioDto.getContrasena());
        usuario.setEstado(usuarioDto.getEstado());
        usuario.setFechaCreacion(usuarioDto.getFechaCreacion());
        usuario.setFechaActualizacion(usuarioDto.getFechaActualizacion());

        return usuario;
    } 
}
