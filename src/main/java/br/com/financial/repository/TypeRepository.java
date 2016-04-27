package br.com.financial.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Type;

public interface TypeRepository extends CrudRepository<Type, Long>{
    
    List<Type> findByDescription(String description);

}
