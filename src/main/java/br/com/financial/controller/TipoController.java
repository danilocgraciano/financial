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

import br.com.financial.model.Tipo;
import br.com.financial.repository.TipoRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/tipos")
public class TipoController extends BaseController{

    @Autowired
    TipoRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Tipo create( @RequestBody @Valid Tipo tipo ) throws Exception{

        List<Tipo> list = repository.findByDescricao(tipo.getDescricao());
        if ( !list.isEmpty() ){
            throw new Exception("Descrição (" + tipo.getDescricao() + ")já utilizada!");
        }

        return repository.save(tipo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Tipo> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            Tipo tipo = mapper.readValue(data, Tipo.class);

            if ( tipo.getDescricao() != null ){
                return repository.findByDescricao(tipo.getDescricao());
            }

        }
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Tipo get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Tipo update( @RequestBody @Valid Tipo tipo ){

        return repository.save(tipo);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
