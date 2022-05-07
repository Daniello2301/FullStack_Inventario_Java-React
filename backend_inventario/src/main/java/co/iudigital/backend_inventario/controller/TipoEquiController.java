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

import co.iudigital.backend_inventario.dto.TipoEquiDto;
import co.iudigital.backend_inventario.exception.BadRequestException;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.InternalServerErrorException;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.service.iface.ITipoService;

@RestController
@RequestMapping("/tipos")
@CrossOrigin("*")
public class TipoEquiController {
    
    private static final Logger LOG = LoggerFactory.getLogger(TipoEquiController.class);

    @Autowired
    private ITipoService tipoService;


    /* *************************************************** LIST ALL Tipos ********************************************** */
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAll() throws RestException 
    {
        LOG.info("Searching");
        
        List<TipoEquiDto> 
                response = 
                        tipoService.getAll();
        
        return ResponseEntity.ok().body(response);
    }

    /* ************************************************ LIST tipo By ID ******************************************************* */
    @GetMapping("/{id}")
    public ResponseEntity<TipoEquiDto> getbyId(@PathVariable Long id) throws RestException
    {
        TipoEquiDto response = tipoService.getById(id);

        return ResponseEntity.ok().body(response);
    }


    /* ************************************************ CREATE Tipo ******************************************************************** */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<TipoEquiDto> create(@RequestBody TipoEquiDto tipoEquiDto) throws RestException
    {
        try 
        {  
            TipoEquiDto response = tipoService.save(tipoEquiDto); 
            
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (BadRequestException e) 
        {
            LOG.error("Erro: " + e);
            throw e;
        } catch (Exception e)
        {
            throw new InternalServerErrorException(ErrorDto
                                                        .getErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                                                                    "Error interno en el servido", 
                                                                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                                                                    )
                                                    );
        }
    }


    /* ********************************************* DELETE tipo By Id *********************************************************************** */

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deltebyId(@PathVariable Long id) throws RestException
    {

        TipoEquiDto tipo = tipoService.getById(id);
        if(tipo == null)
        {
            throw new NotFoundException(ErrorDto.
                    getErrorDto(HttpStatus.NOT_FOUND.getReasonPhrase(),
                    "No se encontro infomacion", 
                    HttpStatus.NOT_FOUND.value()
                                )
                    );
        }else
        {
            tipoService.deleteById(id);
        }

    }

}
























