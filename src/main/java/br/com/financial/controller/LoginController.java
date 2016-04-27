package br.com.financial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.financial.conf.security.TokenHandler;
import br.com.financial.model.User;
import br.com.financial.repository.UserRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class LoginController extends BaseController {

    private TokenHandler tokenHandler;

    public LoginController() {

        this.tokenHandler = new TokenHandler();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @RequestMapping(method = RequestMethod.POST, value = "/api/account")
    public User create( @RequestBody @Valid User user ) throws Exception{

        return userController.create(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/login")
    public ResponseEntity<String> login( @RequestBody String data ) throws Exception{

        String token = "";
        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(data, User.class);

            List<User> list = null;

            if ( user.getEmail() != null && user.getPassword() != null ){
                list = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            }

            if ( !list.isEmpty() ){
                user = list.get(0);
                if ( user.getId() != null && user.getId() > 0 ){
                    token = tokenHandler.create(TokenHandler.APP_WEB_ID, user, 60);
                }

            } else{
                throw new Exception("User not found or invalid!");
            }
        } else{
            throw new Exception("Insufficient Data!");
        }

        return new ResponseEntity<String>(String.format("{\"token\" : \"%s\"}", token), HttpStatus.OK);
    }

}
