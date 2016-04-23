package br.com.financial.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
    List<Usuario> findByEmail(String email);
    
    List<Usuario> findByEmailAndSenha(String email, String senha);

}
