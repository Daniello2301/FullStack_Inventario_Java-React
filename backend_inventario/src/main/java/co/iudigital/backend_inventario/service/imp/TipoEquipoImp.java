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

import co.iudigital.backend_inventario.converter.TipoEquipoConverter;
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
    
    @Autowired
    private TipoEquipoConverter tipoConverter;

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
            TipoEquiDto tipoDto = tipoConverter.tipoEquipoToTipoEquipoDTO(tipo);

            tiposDto.add(tipoDto);
        }

        return tiposDto;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoEquiDto getById(Long id) throws RestException {
        
        TipoEquipo tipo = tipoRepository.findById(id).orElse(null);
        
        TipoEquiDto tipoDto = tipoConverter.tipoEquipoToTipoEquipoDTO(tipo);


        return tipoDto;
    }

    @Override
    @Transactional
    public TipoEquiDto save(TipoEquiDto tipoEquiDto) throws RestException {
       
        LocalDateTime newDate = LocalDateTime.now();
        tipoEquiDto.setFechaActualizacion(newDate);

        TipoEquipo tipo = tipoConverter.tipoEquipoDTOToTipoEquipo(tipoEquiDto);

        TipoEquipo tipoGuardado = tipoRepository.save(tipo);

        tipoEquiDto.setId(tipoGuardado.getId());

        return tipoEquiDto;
    }

    

    @Override
    @Transactional
    public void deleteById(Long id) {
        
        tipoRepository.deleteById(id);
        
    }

    @Override
    public Page<TipoEquipo> paginationTipos(int pageNum, int pageSize) throws RestException {
        
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        Page<TipoEquipo> pageTipos = tipoRepository.findAll(pageable);

        if(pageTipos == null)
        {
            throw new NotFoundException(ErrorDto
                .getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron los datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return pageTipos;
    }

    @Override
    public List<TipoEquiDto> sortByTipos(String field) throws RestException {
        
        List<TipoEquipo> tipos = tipoRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(tipos == null)
        {
            throw new NotFoundException(ErrorDto.getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                                                            "No se encontraron los datos", 
                                                            HttpStatus.NOT_FOUND.value()
                                                            )
                                        );
        }

        List<TipoEquiDto> tiposDTO = new ArrayList<>();

        for(TipoEquipo tipo: tipos){

            TipoEquiDto tipoDTO = tipoConverter.tipoEquipoToTipoEquipoDTO(tipo);

            tiposDTO.add(tipoDTO);

        }

        return tiposDTO;
    }

    @Override
    public Page<TipoEquipo> paginationAndSort(int pageNum, int pageSize, String field) throws RestException {
        
        Pageable pageable = PageRequest.of(pageNum, pageSize).withSort(Sort.by(field));

        Page<TipoEquipo> pageSortTipos = tipoRepository.findAll(pageable);

        if(pageSortTipos == null)
        {
            throw new NotFoundException(ErrorDto
            .getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                            "No se encontraron los datos", 
                            HttpStatus.NOT_FOUND.value()
                            )
                        );
        }

        return pageSortTipos;
    }
    
}
