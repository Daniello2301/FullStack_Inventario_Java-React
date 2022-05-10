package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.dto.EstadoEquiDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.EstadoEquipo;
import co.iudigital.backend_inventario.repository.IEstadoRepository;
import co.iudigital.backend_inventario.service.iface.IEstadoService;

@Service
public class EstadoEquipoImp implements IEstadoService {

    @Autowired
    private IEstadoRepository  estadoRepository;


    @Override
    @Transactional(readOnly = true)
    public List<EstadoEquiDto> getAll() throws RestException {
       
        List<EstadoEquipo> estadosEquipo = estadoRepository.findAll();

        if(estadosEquipo == null)
        {
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        List<EstadoEquiDto> estadosDto = new ArrayList<>();

        for(EstadoEquipo estado: estadosEquipo)
        {
            EstadoEquiDto estadoDto = new EstadoEquiDto();

            estadoDto.setId(estado.getId());
            estadoDto.setNombre(estado.getNombre());
            estadoDto.setEstado(estado.getEstado());
            estadoDto.setFechaCreacion(estado.getFechaCreacion());
            estadoDto.setFechaActualizacion(estado.getFechaActualizacion());

            estadosDto.add(estadoDto);
        }

        return estadosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoEquiDto getById(Long id) throws RestException {
        
        EstadoEquipo estado = estadoRepository.findById(id).orElse(null);

        EstadoEquiDto estadoDto = new EstadoEquiDto();

        estadoDto.setId(estado.getId());
        estadoDto.setNombre(estado.getNombre());
        estadoDto.setEstado(estado.getEstado());
        estadoDto.setFechaCreacion(estado.getFechaCreacion());
        estadoDto.setFechaActualizacion(estado.getFechaActualizacion());

        return estadoDto;
    }

    @Override
    @Transactional
    public EstadoEquiDto save(EstadoEquiDto estadoDto) throws RestException {
        
        EstadoEquipo estado = new EstadoEquipo();

        estado.setNombre(estadoDto.getNombre());
        estado.setEstado(estadoDto.getEstado());
        estado.setFechaCreacion(estadoDto.getFechaCreacion());
        estado.setFechaActualizacion(estadoDto.getFechaActualizacion());

        EstadoEquipo estadoGuardado = estadoRepository.save(estado);

        estadoDto.setId(estadoGuardado.getId());

        return estadoDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        
        estadoRepository.deleteById(id);
        
    }
    
}
