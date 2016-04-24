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

import br.com.financial.model.Parceiro;
import br.com.financial.repository.ParceiroRepository;

@RestController
@RequestMapping("/api/parceiros")
public class ParceiroController extends BaseController{

    @Autowired
    ParceiroRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Parceiro create( @RequestBody @Valid Parceiro parceiro ) throws Exception{

        return repository.save(parceiro);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Parceiro> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Parceiro get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Parceiro update( @RequestBody @Valid Parceiro parceiro ){

        return repository.save(parceiro);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
