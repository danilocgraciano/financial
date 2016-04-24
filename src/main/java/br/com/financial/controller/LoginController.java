package br.com.financial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.financial.conf.security.TokenHandler;
import br.com.financial.model.Usuario;
import br.com.financial.repository.UsuarioRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/login")
public class LoginController extends BaseController {

    private TokenHandler tokenHandler;

    public LoginController() {

        this.tokenHandler = new TokenHandler();
    }

    @Autowired
    UsuarioRepository usuarioRepository;

    @RequestMapping(method = RequestMethod.POST, value = "")
    public ResponseEntity<String> login( @RequestBody String data ) throws Exception{

        String token = "";
        if ( data != null ){
            ObjectMapper mapper = new ObjectMapper();
            Usuario usuario = mapper.readValue(data, Usuario.class);

            List<Usuario> list = null;

            if ( usuario.getEmail() != null && usuario.getSenha() != null ){
                list = usuarioRepository.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
            }

            if ( !list.isEmpty() ){
                usuario = list.get(0);
                if ( usuario.getId() != null && usuario.getId() > 0 ){
                    token = tokenHandler.create(TokenHandler.APP_WEB_ID, usuario, 60);
                }

            } else{
                throw new Exception("Usuário não encontrado ou inválido!");
            }
        } else{
            throw new Exception("Dados insuficientes!");
        }

        return new ResponseEntity<String>(String.format("{\"token\" : \"%s\"}", token), HttpStatus.OK);
    }

}
