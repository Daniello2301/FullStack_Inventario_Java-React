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

import co.iudigital.backend_inventario.converter.EquipoConverter;
import co.iudigital.backend_inventario.dto.EquipoDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Equipo;
import co.iudigital.backend_inventario.repository.IEquipoRepository;
import co.iudigital.backend_inventario.service.iface.IEquipoService;

@Service
public class EquipoImplement implements IEquipoService{
    
    @Autowired
    private IEquipoRepository equipoRepository;

    @Autowired
    private EquipoConverter equipoConverter;

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDto> getAll() throws RestException {
        
        List<Equipo> equipos = equipoRepository.findAll();

        if(equipos == null)
        {
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        List<EquipoDto> equiposDto = new ArrayList<>();

        for(Equipo equipo: equipos)
        {
            EquipoDto equipoDto = equipoConverter.EquipoToEquipoDTO(equipo);

            equiposDto.add(equipoDto);
        }

        return equiposDto;
    }



    @Override
    @Transactional(readOnly = true)
    public EquipoDto getById(Long id) throws RestException {
        
        Equipo equipo = equipoRepository.findById(id).orElse(null);

        EquipoDto equipoDto = equipoConverter.EquipoToEquipoDTO(equipo);


        return equipoDto;
    }




    @Override
    @Transactional
    public EquipoDto save(EquipoDto equipoDto) throws RestException {
       
        LocalDateTime newDate = LocalDateTime.now();
        equipoDto.setFechaActualizacion(newDate);

        Equipo equipo = equipoConverter.EquipoDTOToEquipo(equipoDto);

        Equipo equipoGuardado = equipoRepository.save(equipo);
        
        equipoDto.setId(equipoGuardado.getId());

        return equipoDto;
    }

    @Override
    @Transactional
    public void deleteById(long id){
        
        equipoRepository.deleteById(id);
        
    }



    @Override
    @Transactional
    public Page<Equipo> getEquiposPagination(int pageNumber, int pageSize) throws RestException {
        

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Equipo> equipos = equipoRepository.findAll(pageable);

        if(equipos == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return equipos;
    }


    @Override
    @Transactional
    public List<EquipoDto> getSortBy(String field) throws RestException{

        List<Equipo> equipos = equipoRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(equipos == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        List<EquipoDto> equiposDto = new ArrayList<>();

        for(Equipo equipo: equipos){

            EquipoDto equipoDto = equipoConverter.EquipoToEquipoDTO(equipo);

            equiposDto.add(equipoDto);

        }
        return equiposDto;
    }



    @Override
    @Transactional
    public Page<Equipo> getEquiposPaginaionAndSorting(int pageNumber, int pageSize, String field) throws RestException {
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field));

        Page<Equipo> equipos = equipoRepository.findAll(pageable); 
        
        if(equipos == null){
            throw new NotFoundException(ErrorDto
                    .getErrorDto(
                        HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return equipos;
    }
    
    
}
