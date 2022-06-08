package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.iudigital.backend_inventario.dto.EquipoDto;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Equipo;

public interface IEquipoService {
 
    /* *********************************************************** */
    public Page<Equipo> getEquiposPagination(int pageNumber, int pageSize) throws RestException;

    public List<EquipoDto> getSortBy(String field) throws RestException;

    public Page<Equipo> getEquiposPaginaionAndSorting(int pageNumber, int pageSize, String field) throws RestException;

    /* ********************************************************** */

    public List<EquipoDto> getAll() throws RestException;
    
    public EquipoDto getById(Long id) throws RestException;

    public EquipoDto save(EquipoDto equipoDto) throws RestException;

    public EquipoDto update(EquipoDto equipoDto) throws RestException;

    public void deleteById(long id);
}
