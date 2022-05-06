package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import co.iudigital.backend_inventario.dto.EstadoEquiDto;
import co.iudigital.backend_inventario.exception.RestException;

public interface IEstadoService {
    
    public List<EstadoEquiDto> getAll() throws RestException;

    public EstadoEquiDto getById(Long id) throws RestException;

    public EstadoEquiDto save(EstadoEquiDto estadoDto) throws RestException;

    public void deleteById(Long id) throws RestException;
}
