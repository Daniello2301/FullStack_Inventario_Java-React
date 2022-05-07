package co.iudigital.backend_inventario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.iudigital.backend_inventario.dto.EstadoEquiDto;
import co.iudigital.backend_inventario.exception.BadRequestException;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.InternalServerErrorException;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.service.iface.IEstadoService;

@RestController
@RequestMapping("/estados")
@CrossOrigin("*")
public class EstadoEquiController {
    
    private static final Logger LOG = LoggerFactory.getLogger(EstadoEquiController.class);

    @Autowired
    private IEstadoService estadoService;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAll() throws RestException
    {
        LOG.info("Estados");
        List<EstadoEquiDto> 
                    response = estadoService.getAll();
        
                    return ResponseEntity.ok().body(response);
    }



    @GetMapping("/{id}")
    public ResponseEntity<EstadoEquiDto> getById(@PathVariable Long id) throws RestException
    {

        EstadoEquiDto response = estadoService.getById(id);

        return ResponseEntity.ok().body(response);
    }



    @PostMapping(consumes =  "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<EstadoEquiDto> create(@RequestBody EstadoEquiDto estadoDto) throws RestException
    {
        try 
        {
            EstadoEquiDto response = estadoService.save(estadoDto);
            
            return new ResponseEntity<>(response,
                                        HttpStatus.CREATED); 
        } catch (BadRequestException e) {
            
            LOG.error("Error", e);
            throw e;
        } catch (Exception e)
        {
            throw new InternalServerErrorException(ErrorDto
                        .getErrorDto(   
                            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                            "Error interno del servidor", 
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                            )
                        ); 
        }
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) 
    public void deleteById(@PathVariable Long id) throws RestException
    {

        EstadoEquiDto estado = estadoService.getById(id);

        if(estado == null)
        {
            throw new NotFoundException(ErrorDto
                        .getErrorDto(
                            HttpStatus.NOT_FOUND.getReasonPhrase(), 
                            "No se encuentra", 
                            HttpStatus.NOT_FOUND.value()
                            )
                        );
        } else
        {
            estadoService.deleteById(id); 
        }
    }
}
