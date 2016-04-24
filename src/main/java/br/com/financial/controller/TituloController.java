package br.com.financial.controller;

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

import br.com.financial.model.Titulo;
import br.com.financial.repository.TituloRepository;

@RestController
@RequestMapping("/api/titulos")
public class TituloController extends BaseController{

    @Autowired
    TituloRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Titulo create( @RequestBody @Valid Titulo titulo ) throws Exception{

        return repository.save(titulo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Titulo> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Titulo get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Titulo update( @RequestBody @Valid Titulo Titulo ){

        return repository.save(Titulo);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
