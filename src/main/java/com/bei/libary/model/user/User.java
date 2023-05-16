package com.bei.libary.model.user;

import java.util.ArrayList;
import java
        .util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.bei.libary.model.audit.DateAudit;
import com.bei.libary.model.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode( callSuper = true )
@Entity
@Data
@NoArgsConstructor
@Table( name = "users", uniqueConstraints = { @UniqueConstraint( columnNames = { "username" } ),
        @UniqueConstraint( columnNames = { "email" } ) } )
public class User extends DateAudit {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @NotBlank
    @Column( name = "username" )
    @Size( max = 15 )
    private String username;

    @NotBlank
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY )
    @Size( max = 100 )
    @Column( name = "password" )
    private String password;

    @NotBlank
    @NaturalId
    @Size( max = 40 )
    @Column( name = "email" )
    @Email
    private String email;

    @Column( name = "phone" )
    private String phone;

    @Column( name = "website" )
    private String website;

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "user_role", joinColumns = @JoinColumn( name = "user_id", referencedColumnName = "id" ),
            inverseJoinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ) )
    private List<Role> roles;

    public User(  String username, String email, String password ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public List<Role> getRoles() {

        return roles == null ? null : new ArrayList<>( roles );
    }


    public void setRoles( List<Role> roles ) {

        if( roles == null ) {
            this.roles = null;
        }
        else {
            this.roles = Collections.unmodifiableList( roles );
        }
    }
}
