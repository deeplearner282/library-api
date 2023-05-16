package com.bei.libary.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bei.libary.model.Author;
import com.bei.libary.model.Book;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.BookRequest;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.service.BookService;
import com.bei.libary.exception.BadRequestException;
import com.bei.libary.exception.ResourceNotFoundException;
import com.bei.libary.payload.BookResponse;
import com.bei.libary.repository.AuthorRepository;
import com.bei.libary.repository.BookRepository;
import com.bei.libary.utils.AppConstants;

import static com.bei.libary.utils.AppConstants.AUTHOR;
import static com.bei.libary.utils.AppConstants.BOOK;
import static com.bei.libary.utils.AppConstants.ID;
import static com.bei.libary.utils.AppUtils.validatePageNumberAndSize;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public BookResponse addBook( BookRequest bookRequest ) {
        Book book = new Book();

        Author author = authorRepository.findById( bookRequest.getAuthorId() )
                .orElseThrow( () -> new ResourceNotFoundException( AUTHOR, ID, 1L ) );
        book.setAuthor( author );

        book.setTitle( bookRequest.getTitle() );
        book.setPrice( bookRequest.getPrice() );
        book.setPublicationDate( bookRequest.getPublicationDate() );

        Book newBook = bookRepository.save( book );

        BookResponse bookResponse = new BookResponse();

        bookResponse.setId( newBook.getId() );
        bookResponse.setTitle( newBook.getTitle() );
        bookResponse.setPrice( newBook.getPrice() );
        bookResponse.setPublicationDate( newBook.getPublicationDate() );

        return bookResponse;

    }


    @Override
    public PagedResponse<Book> getAllBooks( PageFilterRequest pageFilterRequest ) {
        validatePageNumberAndSize( pageFilterRequest.getPage(), pageFilterRequest.getSize() );

        Pageable pageable = PageRequest.of( pageFilterRequest.getPage(), pageFilterRequest.getSize(),
                pageFilterRequest.getSortDirection(), pageFilterRequest.getSortingAttribute() );

        Page<Book> books = bookRepository.findAll( pageable );

        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PagedResponse<>( content, books.getNumber(), books.getSize(), books.getTotalElements(),
                books.getTotalPages(), books.isLast() );
    }


    @Override
    public PagedResponse<Book> getAllBooksByAuthorId( Long authorId, PageFilterRequest pageFilterRequest ) {

        validatePageNumberAndSize( pageFilterRequest.getPage(), pageFilterRequest.getSize() );

        Pageable pageable = PageRequest.of( pageFilterRequest.getPage(), pageFilterRequest.getSize(),
                pageFilterRequest.getSortDirection(), pageFilterRequest.getSortingAttribute() );

        Page<Book> books = bookRepository.findAllByAuthorId( authorId, pageable );

        List<Book> content = books.getNumberOfElements() == 0 ? Collections.emptyList() : books.getContent();

        return new PagedResponse<>( content, books.getNumber(), books.getSize(), books.getTotalElements(),
                books.getTotalPages(), books.isLast() );
    }


    @Override
    public Book getBook( Long id ) {
        return bookRepository.findById( id ).orElseThrow( () -> new ResourceNotFoundException( BOOK, ID, id ) );
    }


    @Override
    public Book updateBook( Long id, BookRequest newBookRequest ) {
        Book book = bookRepository.findById( id ).orElseThrow( () -> new ResourceNotFoundException( BOOK, ID, id ) );

        Author author = authorRepository.findById( newBookRequest.getAuthorId() )
                .orElseThrow( () -> new ResourceNotFoundException( AUTHOR, ID, 1L ) );
        book.setAuthor( author );

        book.setTitle( newBookRequest.getTitle() );
        book.setPrice( newBookRequest.getPrice() );
        book.setPublicationDate( newBookRequest.getPublicationDate() );

        return bookRepository.save( book );
    }


    @Override
    public ApiResponse deleteBook( Long id, UserPrincipal currentUser ) {
        bookRepository.deleteById( id );
        return new ApiResponse( Boolean.TRUE, "You successfully deleted author" );
    }


}