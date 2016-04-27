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

import br.com.financial.model.User;
import br.com.financial.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    UserRepository repository;

    @RequestMapping(method = RequestMethod.POST)
    public User create( @RequestBody @Valid User user ) throws Exception{

        List<User> list = repository.findByEmail(user.getEmail());
        if ( !list.isEmpty() ){
            throw new Exception("Email (" + user.getEmail() + ") already used!");
        }

        return repository.save(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public Iterable<User> list( @RequestParam(value = "data", required = false) String data ) throws Exception{

        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(data, User.class);

            if ( user.getEmail() != null && user.getPassword() != null ){
                return repository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            }

            if ( user.getEmail() != null ){
                return repository.findByEmail(user.getEmail());
            }
        }
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public User get( @PathVariable("id") Long id ){

        return repository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User update( @RequestBody @Valid User user ){

        return repository.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Boolean> delete( @PathVariable("id") Long id ){

        repository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

}
