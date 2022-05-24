package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.iudigital.backend_inventario.dto.EquipoDto;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Equipo;
import co.iudigital.backend_inventario.model.EstadoEquipo;
import co.iudigital.backend_inventario.model.Marca;
import co.iudigital.backend_inventario.model.TipoEquipo;
import co.iudigital.backend_inventario.model.Usuario;
import co.iudigital.backend_inventario.repository.IEquipoRepository;
import co.iudigital.backend_inventario.repository.IEstadoRepository;
import co.iudigital.backend_inventario.repository.IMarcaRepository;
import co.iudigital.backend_inventario.repository.ITipoRepository;
import co.iudigital.backend_inventario.repository.IUsuarioRepository;
import co.iudigital.backend_inventario.service.iface.IEquipoService;

@Service
public class EquipoImplement implements IEquipoService{
    
    @Autowired
    private IEquipoRepository equipoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IEstadoRepository estadoRepository;

    @Autowired
    private ITipoRepository tipoRepository;

    @Autowired
    private IMarcaRepository marcaRepository;

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
            EquipoDto equipoDto = new EquipoDto();

            equipoDto.setId(equipo.getId());
            equipoDto.setSerial(equipo.getSerial());
            equipoDto.setModelo(equipo.getModelo());
            equipoDto.setDescripcion(equipo.getDescripcion());
            equipoDto.setImagen(equipo.getFoto());
            equipoDto.setFechaCompra(equipo.getFechaCompra());
            equipoDto.setUsuarioId(equipo.getUsuario().getId());
            equipoDto.setMarcaId(equipo.getMarca().getId());
            equipoDto.setTipoId(equipo.getTipo().getId());
            equipoDto.setEstadoId(equipo.getEstado().getId());
            equipoDto.setFechaCreacion(equipo.getFechaCreacion());
            equipoDto.setFechaActualizacion(equipo.getFechaActualizacion());

            equiposDto.add(equipoDto);
        }

        return equiposDto;
    }



    @Override
    @Transactional(readOnly = true)
    public EquipoDto getById(Long id) throws RestException {
        
        Equipo equipo = equipoRepository.findById(id).orElse(null);

        EquipoDto equipoDto = new EquipoDto();

        equipoDto.setId(equipo.getId());
        equipoDto.setSerial(equipo.getSerial());
        equipoDto.setModelo(equipo.getModelo());
        equipoDto.setDescripcion(equipo.getDescripcion());
        equipoDto.setImagen(equipo.getFoto());
        equipoDto.setFechaCompra(equipo.getFechaCompra());
        equipoDto.setUsuarioId(equipo.getUsuario().getId());
        equipoDto.setMarcaId(equipo.getMarca().getId());
        equipoDto.setTipoId(equipo.getTipo().getId());
        equipoDto.setEstadoId(equipo.getEstado().getId());
        equipoDto.setFechaCreacion(equipo.getFechaCreacion());
        equipoDto.setFechaActualizacion(equipo.getFechaActualizacion());


        return equipoDto;
    }




    @Override
    @Transactional
    public EquipoDto save(EquipoDto equipoDto) throws RestException {
       
        Equipo equipo = new Equipo();

        equipo.setSerial(equipoDto.getSerial());
        equipo.setModelo(equipoDto.getModelo());
        equipo.setDescripcion(equipoDto.getDescripcion());
        equipo.setFoto(equipoDto.getImagen());
        equipo.setPrecio(equipoDto.getPrecio());
        equipo.setFechaCompra(equipoDto.getFechaCompra());

        Optional<Usuario> usuario = usuarioRepository.findById(equipoDto.getUsuarioId());

                if(!usuario.isPresent()){ return null; } 
        equipo.setUsuario(usuario.get());

        Optional<Marca> marca = marcaRepository.findById(equipoDto.getMarcaId());

                if(!marca.isPresent()){return null;}
        equipo.setMarca(marca.get());
        
        Optional<TipoEquipo> tipo = tipoRepository.findById(equipoDto.getTipoId());

                if(!tipo.isPresent()){ return null; }
        equipo.setTipo(tipo.get());

        Optional<EstadoEquipo> estado = estadoRepository.findById(equipoDto.getEstadoId());

                if(!estado.isPresent()){ return null; }
        equipo.setEstado(estado.get());
        
        equipo.setFechaCreacion(equipoDto.getFechaCreacion());
        equipo.setFechaActualizacion(equipoDto.getFechaActualizacion());


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

/*         List<EquipoDto> equiposDto = new ArrayList<>();


        for(Equipo equipo: equipos){
            EquipoDto equipoDto = new EquipoDto();

            equipoDto.setId(equipo.getId());
            equipoDto.setSerial(equipo.getSerial());
            equipoDto.setModelo(equipo.getModelo());
            equipoDto.setDescripcion(equipo.getDescripcion());
            equipoDto.setImagen(equipo.getFoto());
            equipoDto.setFechaCompra(equipo.getFechaCompra());
            equipoDto.setUsuarioId(equipo.getUsuario().getId());
            equipoDto.setMarcaId(equipo.getMarca().getId());
            equipoDto.setTipoId(equipo.getTipo().getId());
            equipoDto.setEstadoId(equipo.getEstado().getId());
            equipoDto.setFechaCreacion(equipo.getFechaCreacion());
            equipoDto.setFechaActualizacion(equipo.getFechaActualizacion());

            equiposDto.add(equipoDto);
        } */ 

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

            EquipoDto equipoDto = new EquipoDto();

            equipoDto.setId(equipo.getId());
            equipoDto.setSerial(equipo.getSerial());
            equipoDto.setModelo(equipo.getModelo());
            equipoDto.setDescripcion(equipo.getDescripcion());
            equipoDto.setImagen(equipo.getFoto());
            equipoDto.setFechaCompra(equipo.getFechaCompra());
            equipoDto.setUsuarioId(equipo.getUsuario().getId());
            equipoDto.setMarcaId(equipo.getMarca().getId());
            equipoDto.setTipoId(equipo.getTipo().getId());
            equipoDto.setEstadoId(equipo.getEstado().getId());
            equipoDto.setFechaCreacion(equipo.getFechaCreacion());
            equipoDto.setFechaActualizacion(equipo.getFechaActualizacion());

            equiposDto.add(equipoDto);

        }
        return equiposDto;
    }



    @Override
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
