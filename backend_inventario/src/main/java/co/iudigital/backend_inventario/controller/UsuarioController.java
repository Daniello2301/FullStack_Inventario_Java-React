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

import co.iudigital.backend_inventario.dto.UsuarioDto;
import co.iudigital.backend_inventario.exception.BadRequestException;
import co.iudigital.backend_inventario.exception.ErrorDto;
import co.iudigital.backend_inventario.exception.InternalServerErrorException;
import co.iudigital.backend_inventario.exception.NotFoundException;
import co.iudigital.backend_inventario.exception.RestException;
import co.iudigital.backend_inventario.model.Usuario;
import co.iudigital.backend_inventario.service.iface.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class); 

    @Autowired
    private IUsuarioService usuarioService;


    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> getAll() throws RestException
    {
        LOG.info("Usuarios");
        List<UsuarioDto> response = usuarioService.getAll();

        return ResponseEntity.ok().body(response);
    }
    


    @GetMapping("/{id}")   
    public ResponseEntity<UsuarioDto> getById(@PathVariable Long id) throws RestException
    {
        UsuarioDto response = usuarioService.getById(id);

        return ResponseEntity.ok().body(response);
    }



    @PostMapping(consumes = "application/json")
    @ResponseStatus
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto) throws RestException
    {
        try 
        {
         UsuarioDto response = usuarioService.save(usuarioDto);
         
         return new ResponseEntity<>(response, HttpStatus.CREATED);
          
        } catch (BadRequestException e) 
        {
            LOG.error("Error", e);
            throw e; 
        } catch (InternalServerErrorException e)
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
        UsuarioDto usuarioDto = usuarioService.getById(id);

        if(usuarioDto == null)
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
            usuarioService.deleteById(id);
        }
    }


    @GetMapping("/pagination/{numPage}/{sizePage}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> usersPagintation(@PathVariable int numPage, @PathVariable int sizePage) throws RestException
    {
        LOG.info("Equipos...");
        Page<Usuario> response = 
                usuarioService.usersPagintation(numPage, sizePage);
        
        return ResponseEntity.ok().body(response);
    }




    @GetMapping("/sortby/{field}")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<Object> usersSortBy(@PathVariable String field) throws RestException
    {
        LOG.info("Equipos...");
        List<UsuarioDto> response = usuarioService.usersSortBy(field);
        
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/paginationAndSort/{numPage}/{sizePage}/{field}")
    public ResponseEntity<Object> usersPagitaionAndSort(@PathVariable int numPage, @PathVariable int sizePage, @PathVariable String field) throws RestException{

        LOG.info("Pagination And Sort Equipos");
        Page<Usuario> response = usuarioService.usersPagitaionAndSort(numPage, sizePage, field);

        return ResponseEntity.ok().body(response);


    }
}
