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

import co.iudigital.backend_inventario.converter.MarcaConverter;
import co.iudigital.backend_inventario.dto.MarcaDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Marca;
import co.iudigital.backend_inventario.repository.IMarcaRepository;
import co.iudigital.backend_inventario.service.iface.IMarcaService;

@Service
public class MarcaImp implements IMarcaService {

    @Autowired
    private IMarcaRepository marcaRepository;

    @Autowired
    private MarcaConverter marcaConverter;

    @Override
    @Transactional(readOnly = true)
    public List<MarcaDto> getAll() throws RestException {
        
        List<Marca> marcas = marcaRepository.findAll(); 
        if(marcas == null)
        {
            throw new NotFoundException(
                ErrorDto.getErrorDto(
                    HttpStatus.NOT_FOUND.getReasonPhrase(), 
                    "NO se encontró", 
                    HttpStatus.NOT_FOUND.value())
            );
        }

        List<MarcaDto> marcasDto = new ArrayList<>();

        for(Marca marca: marcas)
        {
            MarcaDto marcaDto = marcaConverter.marcaToMarcaDTO(marca);

            marcasDto.add(marcaDto);
        }

        return marcasDto;
    }




    @Override
    @Transactional(readOnly = true)
    public MarcaDto getById(Long id) throws RestException {
        
        Marca marca = marcaRepository.findById(id).orElse(null);

        MarcaDto marcaDto = marcaConverter.marcaToMarcaDTO(marca);

        return marcaDto;
    }



    @Override
    @Transactional
    public MarcaDto save(MarcaDto marcaDto) throws RestException {
         
        LocalDateTime newDate = LocalDateTime.now();
        marcaDto.setFechaActualizacion(newDate);

        Marca marca = marcaConverter.marcaDTOToMarca(marcaDto);

        Marca marcaGuardada = marcaRepository.save(marca);

        marcaDto.setId(marcaGuardada.getId());

        return marcaDto;
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        
        marcaRepository.deleteById(id);
        
    }




    @Override
    public Page<Marca> marcasPagination(int numPage, int sizePage) throws RestException {
       
        Pageable pageable = PageRequest.of(numPage, sizePage);

        Page<Marca> marcaPages = marcaRepository.findAll(pageable);

        if(marcaPages == null)
        {
            throw new NotFoundException(ErrorDto
                .getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron los datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return marcaPages;
    }




    @Override
    public List<MarcaDto> marcasSort(String field) throws RestException {
        
        List<Marca> marcasSort = marcaRepository.findAll(Sort.by(Sort.Direction.ASC, field));

        if(marcasSort == null)
        {
            throw new NotFoundException(ErrorDto
                .getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron los datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        List<MarcaDto> marcasDTOSort = new ArrayList<>();

        for(Marca marca : marcasSort){

            MarcaDto marcaDTO = marcaConverter.marcaToMarcaDTO(marca);

            marcasDTOSort.add(marcaDTO);

        }
        return marcasDTOSort;
    }




    @Override 
    public Page<Marca> marcasPaginationAndSort(int numPage, int sizePage, String field) throws RestException {
        
        Pageable pageable = PageRequest.of(numPage, sizePage).withSort(Sort.by(field));

        Page<Marca> marcasPageSort = marcaRepository.findAll(pageable);

        if(marcasPageSort == null)
        {
            throw new NotFoundException(ErrorDto
                .getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(), 
                        "No se encontraron los datos", 
                        HttpStatus.NOT_FOUND.value()
                        )
                    );
        }

        return marcasPageSort;
    }
    
}
