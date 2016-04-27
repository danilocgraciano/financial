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

import br.com.financial.model.Type;
import br.com.financial.repository.TypeRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/types")
public class TypeController extends BaseController{

    @Autowired
    TypeRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Type create( @RequestBody @Valid Type type ) throws Exception{

        List<Type> list = repository.findByDescription(type.getDescription());
        if ( !list.isEmpty() ){
            throw new Exception("Description (" + type.getDescription() + ") already used!");
        }

        return repository.save(type);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Type> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            Type type = mapper.readValue(data, Type.class);

            if ( type.getDescription() != null ){
                return repository.findByDescription(type.getDescription());
            }

        }
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Type get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Type update( @RequestBody @Valid Type type ){

        return repository.save(type);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
