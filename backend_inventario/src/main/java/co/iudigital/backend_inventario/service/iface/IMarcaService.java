package co.iudigital.backend_inventario.service.iface;

import java.util.List;

import co.iudigital.backend_inventario.dto.MarcaDto;
import co.iudigital.backend_inventario.exception.RestException;


public interface IMarcaService {
    
    public List<MarcaDto> getAll() throws RestException;

    public MarcaDto getById(Long id) throws RestException;

    public MarcaDto save(MarcaDto marcaDto) throws RestException;

    public void deleteById(Long id);
}
