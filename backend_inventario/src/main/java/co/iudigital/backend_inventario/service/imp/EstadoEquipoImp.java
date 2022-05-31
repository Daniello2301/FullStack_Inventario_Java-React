package co.iudigital.backend_inventario.service.imp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.converter.EstadoEquipoConverter;
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

    @Autowired
    private EstadoEquipoConverter estadoConverter;

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
            EstadoEquiDto estadoDto = estadoConverter.EstadoEquipoToEstadoEquipoDTO(estado);

            estadosDto.add(estadoDto);
        }

        return estadosDto;
    }

    @Override
    @Transactional(readOnly = true)
    public EstadoEquiDto getById(Long id) throws RestException {
        
        EstadoEquipo estado = estadoRepository.findById(id).orElse(null);

        EstadoEquiDto estadoDto = estadoConverter.EstadoEquipoToEstadoEquipoDTO(estado);

        return estadoDto;
    }

    @Override
    @Transactional
    public EstadoEquiDto save(EstadoEquiDto estadoDto) throws RestException {
        
        LocalDateTime newDate = LocalDateTime.now();
        estadoDto.setFechaActualizacion(newDate);

        EstadoEquipo estado = estadoConverter.EstadoEquipoDTOToEstadoEquipo(estadoDto);

        EstadoEquipo estadoGuardado = estadoRepository.save(estado);

        estadoDto.setId(estadoGuardado.getId());

        return estadoDto;
    }



    @Override
    @Transactional
    public void deleteById(Long id){
        
        estadoRepository.deleteById(id);
        
    }


    
    @Override
    public Page<EstadoEquipo> estadosPagination(int numPage, int sizePage) throws RestException {
        Pageable pageable = PageRequest.of(numPage, sizePage);
        Page<EstadoEquipo> pageEstados = estadoRepository.findAll(pageable);

        if(pageEstados == null)
        {
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return pageEstados;
    }

    @Override
    public List<EstadoEquiDto> estadosSort(String field) throws RestException {

        List<EstadoEquipo> estadosSort = estadoRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        if(estadosSort == null)
        {
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        List<EstadoEquiDto> estadosDTO = new ArrayList<>();
        for(EstadoEquipo estado: estadosSort)
        {
            EstadoEquiDto estadoDTO = estadoConverter.EstadoEquipoToEstadoEquipoDTO(estado);

            estadosDTO.add(estadoDTO);
        }
        return estadosDTO;
    }

    @Override
    public Page<EstadoEquipo> estadosPaginationAndSort(int numPage, int sizePage, String field) throws RestException {
        
        Pageable pageable = PageRequest.of(numPage, sizePage).withSort(Sort.by(field));

        Page<EstadoEquipo> estadosSorPaginaion = estadoRepository.findAll(pageable);

        if(estadosSorPaginaion == null)
        {
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }
        return estadosSorPaginaion;
    }
    
}
