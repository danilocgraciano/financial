package br.com.financial.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private float valor;

	@NotEmpty
	@Column(nullable = false)
	@Temporal(value=TemporalType.DATE)
	private Date dataVcto;

	@OneToOne
	private Tipo tipo;

	private String formaPgto;

	private String descricao;

	private byte[] comprovante;

}
