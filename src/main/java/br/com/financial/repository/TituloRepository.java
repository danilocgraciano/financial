package br.com.financial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Titulo;

public interface TituloRepository extends CrudRepository<Titulo, Long>{

}
