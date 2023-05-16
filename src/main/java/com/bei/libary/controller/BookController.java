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

import com.bei.libary.model.Book;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.BookRequest;
import com.bei.libary.payload.BookResponse;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.service.BookService;
import com.bei.libary.security.CurrentUser;
import com.bei.libary.utils.AppConstants;


@RestController
@RequestMapping( "/api/books" )
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<BookResponse> addBook( @Valid @RequestBody BookRequest bookRequest ) {
        BookResponse bookResponse = bookService.addBook( bookRequest );

        return new ResponseEntity<>( bookResponse, HttpStatus.CREATED );
    }


    @GetMapping
    public ResponseEntity<PagedResponse<Book>> getAllBooks( PageFilterRequest pageFilterRequest ) {
        PagedResponse<Book> response = bookService.getAllBooks(pageFilterRequest);

        return new ResponseEntity<>( response, HttpStatus.OK );
    }


    @GetMapping( "/author/{authorId}" )
    public ResponseEntity<PagedResponse<Book>> getAllBooksByAuthor( @PathVariable Long authorId,
            PageFilterRequest pageFilterRequest ) {
        PagedResponse<Book> response = bookService.getAllBooksByAuthorId( authorId, pageFilterRequest );

        return new ResponseEntity<>( response, HttpStatus.OK );
    }


    @GetMapping( "/{id}" )
    public ResponseEntity<Book> getBook( @PathVariable( name = "id" ) Long id ) {
        Book book = bookService.getBook( id );

        return new ResponseEntity<>( book, HttpStatus.OK );
    }


    @PutMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Book> updateBook( @PathVariable( name = "id" ) Long id,
            @Valid @RequestBody BookRequest newBookRequest ) {
        Book book = bookService.updateBook( id, newBookRequest );

        return new ResponseEntity<>( book, HttpStatus.OK );
    }


    @DeleteMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<ApiResponse> deleteBook( @PathVariable( name = "id" ) Long id,
            @CurrentUser UserPrincipal currentUser ) {
        ApiResponse apiResponse = bookService.deleteBook( id, currentUser );

        return new ResponseEntity<>( apiResponse, HttpStatus.OK );
    }

}
