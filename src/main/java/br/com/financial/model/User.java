package br.com.financial.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String password;

    
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

    
    public String getPassword(){
    
        return password;
    }

    
    public void setPassword( String password ){
    
        this.password = password;
    }

}
