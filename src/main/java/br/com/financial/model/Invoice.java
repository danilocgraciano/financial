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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.financial.conf.CustomDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(0)
    @Column(nullable = false)
    private float value;

    @NotNull
    @Column(nullable = false)
    @Temporal(value = TemporalType.DATE)
    @JsonSerialize(using=CustomDateSerializer.class)
    private Date dueDate;

    @OneToOne
    @NotNull
    private Type type;

    @OneToOne
    private Partner partner;

    private String paymentForm;

    private String description;

    private byte[] receipt;

    public Long getId(){

        return id;
    }

    public void setId( Long id ){

        this.id = id;
    }

    public float getValue(){

        return value;
    }

    public void setValue( float value ){

        this.value = value;
    }

    public Date getDueDate(){

        return dueDate;
    }

    public void setDueDate( Date dueDate ){

        this.dueDate = dueDate;
    }

    public Type getType(){

        return type;
    }

    public void setType( Type type ){

        this.type = type;
    }

    public Partner getPartner(){

        return partner;
    }

    public void setPartner( Partner partner ){

        this.partner = partner;
    }

    public String getPaymentForm(){

        return paymentForm;
    }

    public void setPaymentForm( String paymentForm ){

        this.paymentForm = paymentForm;
    }

    public String getDescription(){

        return description;
    }

    public void setDescription( String description ){

        this.description = description;
    }

    public byte[] getReceipt(){

        return receipt;
    }

    public void setReceipt( byte[] receipt ){

        this.receipt = receipt;
    }

}
