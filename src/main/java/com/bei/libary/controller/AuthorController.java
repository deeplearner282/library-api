package com.bei.libary.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bei.libary.model.Author;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.AuthorRequest;
import com.bei.libary.payload.AuthorResponse;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.service.AuthorService;
import com.bei.libary.security.CurrentUser;
import com.bei.libary.utils.AppConstants;


@RestController
@RequestMapping( "/api/authors" )
public class AuthorController {
    @Autowired
    private AuthorService authorService;


    @PostMapping
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<AuthorResponse> addAuthor( @Valid @RequestBody AuthorRequest authorRequest ) {
        AuthorResponse authorResponse = authorService.addAuthor( authorRequest );

        return new ResponseEntity<>( authorResponse, HttpStatus.CREATED );
    }


    @GetMapping
    public ResponseEntity<PagedResponse<Author>> getAllAuthors( PageFilterRequest pageFilterRequest ) {
        PagedResponse<Author> response = authorService.getAllAuthors(pageFilterRequest);

        return new ResponseEntity<>( response, HttpStatus.OK );
    }


    @GetMapping( "/{id}" )
    public ResponseEntity<Author> getAuthor( @PathVariable( name = "id" ) Long id ) {
        Author author = authorService.getAuthor( id );

        return new ResponseEntity<>( author, HttpStatus.OK );
    }


    @PutMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Author> updateAuthor( @PathVariable( name = "id" ) Long id,
            @Valid @RequestBody AuthorRequest newAuthorRequest ) {
        Author author = authorService.updateAuthor( id, newAuthorRequest );

        return new ResponseEntity<>( author, HttpStatus.OK );
    }


    @DeleteMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<ApiResponse> deleteAuthor( @PathVariable( name = "id" ) Long id,
            @CurrentUser UserPrincipal currentUser ) {
        ApiResponse apiResponse = authorService.deleteAuthor( id, currentUser );

        return new ResponseEntity<>( apiResponse, HttpStatus.OK );
    }

}
