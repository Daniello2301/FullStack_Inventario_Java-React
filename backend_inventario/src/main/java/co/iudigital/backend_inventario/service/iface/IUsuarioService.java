package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.exception.RestException;

public interface IUsuarioService {
    
    public List<UsuarioDto> getAll() throws RestException;

    public UsuarioDto getById(Long id) throws RestException;

    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException;

    public void deleteById(Long id) throws RestException;
}
