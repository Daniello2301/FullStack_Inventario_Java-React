package co.iudigital.backend_inventario.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.converter.UsuarioConverter;
import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Usuario;
import co.iudigital.backend_inventario.repository.IUsuarioRepository;
import co.iudigital.backend_inventario.service.iface.IUsuarioService;

@Service
public class UsuarioImp implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAll() throws RestException {

        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios == null) {
            throw new NotFoundException(
                    ErrorDto.getErrorDto(
                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                            "NO se encontró",
                            HttpStatus.NOT_FOUND.value()));
        }

        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for (Usuario usuario : usuarios) {

            UsuarioDto usuarioDto = usuarioConverter.usuarioToUsuarioDTO(usuario);

            usuariosDto.add(usuarioDto);
        }
        return usuariosDto;
    }




    @Override 
    @Transactional(readOnly = true)
    public UsuarioDto getById(Long id) throws RestException {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        UsuarioDto usuarioDto = usuarioConverter.usuarioToUsuarioDTO(usuario);

        return usuarioDto;
    }





    @Override
    @Transactional
    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException {
        
        LocalDateTime dateSave = LocalDateTime.now();
        
        usuarioDto.setFechaCreacion(dateSave);
        usuarioDto.setFechaActualizacion(dateSave);
        
        Usuario usuario = usuarioConverter.usuarioDTOToUsuario(usuarioDto);

        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        usuarioDto.setId(usuarioGuardado.getId());

        return usuarioDto;
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioDto usuarioDto) throws RestException {
        LocalDateTime newDate = LocalDateTime.now();
        usuarioDto.setFechaActualizacion(newDate);

        Usuario usuario = usuarioRepository.findById(usuarioConverter.usuarioDTOToUsuario(usuarioDto).getId()).orElse(null);

        if(usuario  == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        Usuario usuarioSave = usuarioRepository.save(usuario);

        usuarioDto.setId(usuarioSave.getId());

        return usuarioDto;
    }


    @Override
    @Transactional
    public void deleteById(Long id) {

        usuarioRepository.deleteById(id);

    }




    @Override
    public Page<Usuario> usersPagintation(int numPage, int sizePage) throws RestException {
        
        Pageable pageable = PageRequest.of(numPage, sizePage);
        
        Page<Usuario> pageUsuarios = usuarioRepository.findAll(pageable);

        if(pageUsuarios == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }
        return pageUsuarios;
    }




    @Override
    public List<UsuarioDto> usersSortBy(String field) throws RestException {     
        
        List<Usuario> usuarios = usuarioRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(usuarios == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }
        
        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for(Usuario usuario: usuarios){

            UsuarioDto usuarioDto = usuarioConverter.usuarioToUsuarioDTO(usuario);

            usuariosDto.add(usuarioDto);
        }

        return usuariosDto;
    }




    @Override
    public Page<Usuario> usersPagitaionAndSort(int numPage, int sizePage, String field) throws RestException {
        
        Pageable pageable = PageRequest.of(numPage, sizePage).withSort(Sort.by(field));

        Page<Usuario> usuarios = usuarioRepository.findAll(pageable); 
        
        if(usuarios == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return usuarios;
    }

}
