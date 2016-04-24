package br.com.financial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.financial.model.Usuario;
import br.com.financial.repository.UsuarioRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends BaseController{

    @Autowired
    UsuarioRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Usuario create( @RequestBody @Valid Usuario usuario ) throws Exception{

        List<Usuario> list = repository.findByEmail(usuario.getEmail());
        if ( !list.isEmpty() ){
            throw new Exception("Email (" + usuario.getEmail() + ") já utilizado!");
        }

        return repository.save(usuario);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Usuario> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            Usuario user = mapper.readValue(data, Usuario.class);

            if ( user.getEmail() != null && user.getSenha() != null ){
                return repository.findByEmailAndSenha(user.getEmail(), user.getSenha());
            }

            if ( user.getEmail() != null ){
                return repository.findByEmail(user.getEmail());
            }
        }
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Usuario get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Usuario update( @RequestBody @Valid Usuario usuario ){

        return repository.save(usuario);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
