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

import br.com.financial.model.Invoice;
import br.com.financial.repository.InvoiceRepository;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController extends BaseController{

    @Autowired
    InvoiceRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public Invoice create( @RequestBody @Valid Invoice invoice ) throws Exception{

        return repository.save(invoice);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<Invoice> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Invoice get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Invoice update( @RequestBody @Valid Invoice invoice ){

        return repository.save(invoice);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
