package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.iudigital.backend_inventario.dto.MarcaDto;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Marca;


public interface IMarcaService {
    
    /* ****************** PAGINATION AND SORT ************************ */
    public Page<Marca> marcasPagination(int numPage, int sizePage) throws RestException;

    public List<MarcaDto> marcasSort(String field) throws RestException;

    public Page<Marca> marcasPaginationAndSort(int numPage, int sizePage, String field) throws RestException;

    /* ************** CRUD METODS ************** */
    public List<MarcaDto> getAll() throws RestException;

    public MarcaDto getById(Long id) throws RestException;

    public MarcaDto save(MarcaDto marcaDto) throws RestException;

    public void deleteById(Long id);
}
