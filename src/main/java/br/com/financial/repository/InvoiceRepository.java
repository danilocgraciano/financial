package br.com.financial.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.financial.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
