package com.bei.libary.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode
@Entity
@Data
@Table( name = "books" )
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "id" )
public class Book {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private String title;

    @Column
    private Instant publicationDate;

    @Column
    private BigDecimal price;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "author_id" )
    private Author author;


    @JsonIgnore
    public Author getAuthor() {
        return author;
    }

}

