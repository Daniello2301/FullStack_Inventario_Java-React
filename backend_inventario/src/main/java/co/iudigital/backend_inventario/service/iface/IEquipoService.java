package co.iudigital.backend_inventario.service.iface;

import java.util.List;


import co.iudigital.backend_inventario.dto.EquipoDto;
import co.iudigital.backend_inventario.exception.RestException;

public interface IEquipoService {
 
    public List<EquipoDto> getAll() throws RestException;
    
    public EquipoDto getById(Long id) throws RestException;

    public EquipoDto save(EquipoDto equipoDto) throws RestException;

    public void deleteById(long id);
}
