package br.com.financial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Partner {

    public Partner() {

    }

    @JsonCreator
    public Partner( String jsonData ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Partner e = mapper.readValue(jsonData, this.getClass());
        this.setAddress(e.getAddress());
        this.setCity(e.getCity());
        this.setComplement(e.getComplement());
        this.setCpfCnpj(e.getCpfCnpj());
        this.setEmail(e.getEmail());
        this.setId(e.getId());
        this.setName(e.getName());
        this.setNeighborhood(e.getNeighborhood());
        this.setNote(e.getNote());
        this.setNumber(e.getNumber());
        this.setPhone(e.getPhone());
        this.setState(e.getState());
        this.setZipCode(e.getZipCode());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Email
    private String email;

    @Column(unique = true)
    private String cpfCnpj;

    private String phone;

    private String zipCode;

    private String address;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    private String note;

    public Long getId(){

        return id;
    }

    public void setId( Long id ){

        this.id = id;
    }

    public String getName(){

        return name;
    }

    public void setName( String name ){

        this.name = name;
    }

    public String getEmail(){

        return email;
    }

    public void setEmail( String email ){

        this.email = email;
    }

    public String getCpfCnpj(){

        return cpfCnpj;
    }

    public void setCpfCnpj( String cpfCnpj ){

        this.cpfCnpj = cpfCnpj;
    }

    public String getPhone(){

        return phone;
    }

    public void setPhone( String phone ){

        this.phone = phone;
    }

    public String getZipCode(){

        return zipCode;
    }

    public void setZipCode( String zipCode ){

        this.zipCode = zipCode;
    }

    public String getAddress(){

        return address;
    }

    public void setAddress( String address ){

        this.address = address;
    }

    public String getNumber(){

        return number;
    }

    public void setNumber( String number ){

        this.number = number;
    }

    public String getComplement(){

        return complement;
    }

    public void setComplement( String complement ){

        this.complement = complement;
    }

    public String getNeighborhood(){

        return neighborhood;
    }

    public void setNeighborhood( String neighborhood ){

        this.neighborhood = neighborhood;
    }

    public String getCity(){

        return city;
    }

    public void setCity( String city ){

        this.city = city;
    }

    public String getState(){

        return state;
    }

    public void setState( String state ){

        this.state = state;
    }

    public String getNote(){

        return note;
    }

    public void setNote( String note ){

        this.note = note;
    }

}
