package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import org.springframework.data.domain.Page;

import co.iudigital.backend_inventario.dto.EstadoEquiDto;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.EstadoEquipo;

public interface IEstadoService {
    
    public Page<EstadoEquipo> estadosPagination(int numPage, int sizePage) throws RestException;

    public List<EstadoEquiDto> estadosSort(String field) throws RestException;

    public Page<EstadoEquipo> estadosPaginationAndSort(int numPage, int sizePage, String field) throws RestException;


    public List<EstadoEquiDto> getAll() throws RestException;

    public EstadoEquiDto getById(Long id) throws RestException;

    public EstadoEquiDto save(EstadoEquiDto estadoDto) throws RestException;

    public void deleteById(Long id);
}
