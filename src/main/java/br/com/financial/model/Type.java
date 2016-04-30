package br.com.financial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Type {

    public Type() {

    }

    @JsonCreator
    public Type( String jsonData ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Type e = mapper.readValue(jsonData, this.getClass());

        this.setDescription(e.getDescription());
        this.setId(e.getId());
        this.setType(e.getType());

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private byte type;

    public Long getId(){

        return id;
    }

    public void setId( Long id ){

        this.id = id;
    }

    public String getDescription(){

        return description;
    }

    public void setDescription( String description ){

        this.description = description;
    }

    public byte getType(){

        return type;
    }

    public void setType( byte type ){

        this.type = type;
    }

}
