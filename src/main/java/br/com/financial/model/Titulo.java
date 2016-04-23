package br.com.financial.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

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
    @Temporal(value = TemporalType.DATE)
    private Date dataVcto;

    @OneToOne
    @NotNull
    private Tipo tipo;

    @OneToOne
    private Parceiro parceiro;

    private String formaPgto;

    private String descricao;

    private byte[] comprovante;

    public Long getId(){

        return id;
    }

    public void setId( Long id ){

        this.id = id;
    }

    public float getValor(){

        return valor;
    }

    public void setValor( float valor ){

        this.valor = valor;
    }

    public Date getDataVcto(){

        return dataVcto;
    }

    public void setDataVcto( Date dataVcto ){

        this.dataVcto = dataVcto;
    }

    public Tipo getTipo(){

        return tipo;
    }

    public void setTipo( Tipo tipo ){

        this.tipo = tipo;
    }

    public String getFormaPgto(){

        return formaPgto;
    }

    public void setFormaPgto( String formaPgto ){

        this.formaPgto = formaPgto;
    }

    public String getDescricao(){

        return descricao;
    }

    public void setDescricao( String descricao ){

        this.descricao = descricao;
    }

    public byte[] getComprovante(){

        return comprovante;
    }

    public void setComprovante( byte[] comprovante ){

        this.comprovante = comprovante;
    }

}
