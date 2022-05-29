package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Usuario;

public interface IUsuarioService { 
    
    /* ******************** User Pagintacion And Sort ******************************************* */

    Page<Usuario> usersPagintation (int numPage, int sizePage) throws RestException;

    List<UsuarioDto> usersSortBy (String field) throws RestException;

    Page<Usuario> usersPagitaionAndSort(int numPage, int sizePage, String field) throws RestException;


    /* ********************** User CRUD Methods ************************************************** */
    public List<UsuarioDto> getAll() throws RestException;

    public UsuarioDto getById(Long id) throws RestException;

    public UsuarioDto save(UsuarioDto usuarioDto) throws RestException;

    public UsuarioDto update(UsuarioDto usuarioDto) throws RestException;

    public void deleteById(Long id);
}
