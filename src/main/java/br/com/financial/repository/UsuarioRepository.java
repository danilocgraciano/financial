package br.com.financial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
