package co.iudigital.backend_inventario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import co.iudigital.backend_inventario.dto.MarcaDto;
import co.iudigital.backend_inventario.exception.BadRequestException;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.InternalServerErrorException;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Marca;
import co.iudigital.backend_inventario.service.iface.IMarcaService;

@RestController
@RequestMapping("/marcas")
@CrossOrigin("*")
public class MarcaController {
    
    private static final Logger LOG = LoggerFactory.getLogger(MarcaController.class);

    @Autowired
    private IMarcaService marcaService;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAll() throws RestException
    {
        LOG.info("Marcas..-");
        List<MarcaDto> response = marcaService.getAll();

        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> getById(@PathVariable Long id) throws RestException
    {
        MarcaDto response = marcaService.getById(id);

        return ResponseEntity.ok().body(response);
    }



    @PostMapping(consumes = "application/json")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<MarcaDto> create(@RequestBody MarcaDto marcaDto) throws RestException
    {
        try 
        {
            MarcaDto response = marcaService.save(marcaDto);
            
            return new ResponseEntity<>(response,
                                    HttpStatus.CREATED);

        } catch (BadRequestException e) {
            
            LOG.error("Error", e);
            throw e;

        }catch (InternalServerErrorException e)
        {
            throw new InternalServerErrorException(
                ErrorDto.getErrorDto(
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
                    "Error interno en el servidor",
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
            );
        }
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws RestException
    {
        MarcaDto marca = marcaService.getById(id);

        if(marca == null)
        {
            throw new NotFoundException(ErrorDto
                        .getErrorDto(
                            HttpStatus.NOT_FOUND.getReasonPhrase(), 
                            "No se encuentra", 
                            HttpStatus.NOT_FOUND.value()
                            )
                        );
        }else
        {
            marcaService.deleteById(id); 
        }
    }


    @GetMapping("/pagination/{numPage}/{sizePage}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> marcasPagination(@PathVariable int numPage, @PathVariable int sizePage) throws RestException
    {
        LOG.info("Equipos...");
        Page<Marca> response = marcaService.marcasPagination(numPage, sizePage);
        
        return ResponseEntity.ok().body(response);
    }




    @GetMapping("/sortby/{field}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> marcasSort(@PathVariable String field) throws RestException
    {
        LOG.info("Equipos...");
        List<MarcaDto> response = marcaService.marcasSort(field);
        
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/paginationAndSort/{numPage}/{sizePage}/{field}")
    public ResponseEntity<Object> marcasPaginationAndSort(@PathVariable int numPage, @PathVariable int sizePage, @PathVariable String field) throws RestException{

        LOG.info("Pagination And Sort Equipos");
        Page<Marca> response = marcaService.marcasPaginationAndSort(numPage, sizePage, field);

        return ResponseEntity.ok().body(response);


    }
}
