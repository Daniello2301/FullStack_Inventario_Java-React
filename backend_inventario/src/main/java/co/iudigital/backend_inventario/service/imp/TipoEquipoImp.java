package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.TipoEquipo;
import co.iudigital.backend_inventario.repository.ITipoRepository;
import co.iudigital.backend_inventario.service.iface.ITipoService;

@Service
public class TipoEquipoImp implements ITipoService {

    @Autowired
    private ITipoRepository tipoRepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoEquiDto> getAll() throws RestException {
       
        List<TipoEquipo> tiposEquipo = tipoRepository.findAll();

        if(tiposEquipo == null)
        {
            throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                            "No se encontraron los datos", 
                                                            HttpStatus.NOT_FOUND.value()
                                                            )
                                        );
        }
        List<TipoEquiDto> tiposDto = new ArrayList<>();

        for(TipoEquipo tipo: tiposEquipo)
        {
            TipoEquiDto tipoDto = new TipoEquiDto();
            
            tipoDto.setId(tipo.getId());
            tipoDto.setNombre(tipo.getNombre());
            tipoDto.setEstado(tipo.getEstado());
            tipoDto.setFechaCreacion(tipo.getFechaCreacion());
            tipoDto.setFechasActualizacion(tipo.getFechaActualizacion());

            tiposDto.add(tipoDto);
        }

        return tiposDto;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoEquiDto getById(Long id) throws RestException {
        
        TipoEquipo tipo = tipoRepository.findById(id).orElse(null);
        TipoEquiDto tipoDto = new TipoEquiDto();

        tipoDto.setId(tipo.getId());
        tipoDto.setNombre(tipo.getNombre());
        tipoDto.setEstado(tipo.getNombre());
        tipoDto.setFechaCreacion(tipo.getFechaCreacion());
        tipoDto.setFechasActualizacion(tipo.getFechaActualizacion());

        return tipoDto;
    }

    @Override
    @Transactional
    public TipoEquiDto save(TipoEquiDto tipoEquiDto) throws RestException {
        
        TipoEquipo tipo = new TipoEquipo();

        tipo.setNombre(tipoEquiDto.getNombre());
        tipo.setEstado(tipoEquiDto.getEstado());
        tipo.setFechaCreacion(tipoEquiDto.getFechaCreacion());
        tipo.setFechaActualizacion(tipoEquiDto.getFechasActualizacion());

        TipoEquipo tipoGuardado = tipoRepository.save(tipo);

        tipo.setId(tipoGuardado.getId());

        return tipoEquiDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws RestException {
        
        tipoRepository.deleteById(id);
        
    }
    
}
