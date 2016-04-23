package br.com.financial.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Tipo;

public interface TipoRepository extends CrudRepository<Tipo, Long>{
    
    List<Tipo> findByDescricao(String descricao);

}
