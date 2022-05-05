package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.exception.RestException;

public interface ITipoService {

    public List<TipoEquiDto> getAll() throws RestException;

    public TipoEquiDto getById(Long id) throws RestException;

    public TipoEquiDto save(TipoEquiDto tipoEquiDto) throws RestException;

    public void deleteById(Long id) throws RestException;
}
