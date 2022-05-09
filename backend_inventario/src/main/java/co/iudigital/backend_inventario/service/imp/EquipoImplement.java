package co.iudigital.backend_inventario.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
            equipoDto.setUsuarioId(equipo.getUsuarioId().getId());
            equipoDto.setMarcaId(equipo.getMarcaId().getId());
            equipoDto.setTipoId(equipo.getTipoId().getId());
            equipoDto.setEstadoId(equipo.getEstadoId().getId());
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
        equipoDto.setUsuarioId(equipo.getUsuarioId().getId());
        equipoDto.setMarcaId(equipo.getMarcaId().getId());
        equipoDto.setTipoId(equipo.getTipoId().getId());
        equipoDto.setEstadoId(equipo.getEstadoId().getId());
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
        equipo.setFechaCompra(equipoDto.getFechaCompra());
        Optional<Usuario> usuario = 
                usuarioRepository.findById(equipoDto.getUsuarioId()); 
                if(!usuario.isPresent()){ return null; }
        equipo.setUsuarioId(usuario.get());
        Optional<Marca> marca =
                marcaRepository.findById(equipoDto.getMarcaId());
                if(!marca.isPresent()){return null;}
        equipo.setMarcaId(marca.get());
        Optional<TipoEquipo> tipo =
                tipoRepository.findById(equipoDto.getTipoId());
                if(!tipo.isPresent()){ return null; }
        equipo.setTipoId(tipo.get());
        Optional<EstadoEquipo> estado = 
                estadoRepository.findById(equipoDto.getEstadoId());
                if(!estado.isPresent()){ return null; }
        equipo.setEstadoId(estado.get());

        Equipo equipoGuardado = equipoRepository.save(equipo);
        
        equipoDto.setId(equipoGuardado.getId());

        return equipoDto;
    }

    @Override
    public void deleteById(long id) throws RestException {
        
        equipoRepository.deleteById(id);
        
    }
    
}
