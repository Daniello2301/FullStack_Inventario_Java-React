package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Usuario;
import co.iudigital.backend_inventario.repository.IUsuarioRepository;
import co.iudigital.backend_inventario.service.iface.IUsuarioService;

@Service
public class UsuarioImp  implements IUsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAll() throws RestException {
        
        List<Usuario> usuarios = usuarioRepository.findAll();
    
        if(usuarios == null)
        {
            throw new NotFoundException(
                ErrorDto.getErrorDto(
                    HttpStatus.NOT_FOUND.getReasonPhrase(), 
                    "NO se encontró", 
                    HttpStatus.NOT_FOUND.value())
            );
        }
    
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for(Usuario usuario: usuarios)
        {
            UsuarioDto usuarioDto = new UsuarioDto();

            usuarioDto.setId(usuario.getId());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setEstado(usuario.getEstado());
            usuarioDto.setFechaCreacion(usuario.getFechaCreacion());
            usuarioDto.setFechaActualizacion(usuario.getFechaActualizacion());

            usuariosDto.add(usuarioDto);
        }
        return usuariosDto;
    }


    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getById(Long id) throws RestException {
        
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setId(usuario.getId()); 
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEstado(usuario.getEstado());
        usuarioDto.setFechaCreacion(usuario.getFechaCreacion());
        usuarioDto.setFechaActualizacion(usuario.getFechaActualizacion());

        return usuarioDto;
    }

    @Override
    @Transactional
    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException {
        
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEstado(usuarioDto.getEstado());
        usuario.setFechaCreacion(usuarioDto.getFechaCreacion());
        usuario.setFechaActualizacion(usuarioDto.getFechaActualizacion());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        usuarioDto.setId(usuarioGuardado.getId());

        return usuarioDto;
    }

    @Override
    public void deleteById(Long id) throws RestException {
        
        usuarioRepository.deleteById(id);
        
    }
    
}
