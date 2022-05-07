package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            MarcaDto marcaDto = new MarcaDto();

            marcaDto.setId(marca.getId());
            marcaDto.setNombre(marca.getNombre());
            marcaDto.setEstado(marca.getEstado());
            marcaDto.setFechaCreacion(marca.getFechaCreacion());
            marcaDto.setFechaActualizacion(marca.getFechaActualizacion());

            marcasDto.add(marcaDto);
        }

        return marcasDto;
    }




    @Override
    @Transactional(readOnly = true)
    public MarcaDto getById(Long id) throws RestException {
        
        Marca marca = marcaRepository.findById(id).orElse(null);

        MarcaDto marcaDto = new MarcaDto();

        marcaDto.setId(marca.getId());
        marcaDto.setNombre(marca.getNombre());
        marcaDto.setEstado(marca.getEstado());
        marcaDto.setFechaCreacion(marca.getFechaCreacion());
        marcaDto.setFechaActualizacion(marca.getFechaActualizacion());

        return marcaDto;
    }


    @Override
    @Transactional
    public MarcaDto save(MarcaDto marcaDto) throws RestException {
        
        Marca marca = new Marca();

        marca.setNombre(marcaDto.getNombre());
        marca.setEstado(marcaDto.getEstado());
        marca.setFechaCreacion(marcaDto.getFechaCreacion());
        marca.setFechaActualizacion(marcaDto.getFechaActualizacion());

        Marca marcaGuardada = marcaRepository.save(marca);

        marcaDto.setId(marcaGuardada.getId());

        return marcaDto;
    }

    @Override
    public void deleteById(Long id) throws RestException {
        
        marcaRepository.deleteById(id);
        
    }
    
}
