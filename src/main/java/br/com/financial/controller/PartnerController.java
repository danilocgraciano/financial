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

import br.com.financial.model.Partner;
import br.com.financial.repository.PartnerRepository;

@RestController
@RequestMapping("/api/partners")
public class PartnerController extends BaseController {

    @Autowired
    PartnerRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Partner create( @RequestBody @Valid Partner partner ) throws Exception{

        return repository.save(partner);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Partner> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Partner get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Partner update( @RequestBody @Valid Partner partner ){

        return repository.save(partner);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
