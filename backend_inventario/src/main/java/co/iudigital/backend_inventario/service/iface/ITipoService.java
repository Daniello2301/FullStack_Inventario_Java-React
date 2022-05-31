package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.TipoEquipo;

public interface ITipoService {

    /* ***** PAGINATION AND SORT ********************* */

    public Page<TipoEquipo> paginationTipos(int pageNum, int pageSize) throws RestException;

    public List<TipoEquiDto> sortByTipos(String field) throws RestException;

    public Page<TipoEquipo> paginationAndSort(int pageNum, int pageSize, String field ) throws RestException;


    /* **** CRUD METODS **************************** */
    public List<TipoEquiDto> getAll() throws RestException;

    public TipoEquiDto getById(Long id) throws RestException;

    public TipoEquiDto save(TipoEquiDto tipoEquiDto) throws RestException;

    public void deleteById(Long id);
}
