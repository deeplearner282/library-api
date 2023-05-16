package com.bei.libary.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bei.libary.exception.ResourceNotFoundException;
import com.bei.libary.model.Author;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.AuthorRequest;
import com.bei.libary.payload.AuthorResponse;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.repository.AuthorRepository;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.service.AuthorService;

import static com.bei.libary.utils.AppConstants.AUTHOR;
import static com.bei.libary.utils.AppConstants.ID;
import static com.bei.libary.utils.AppUtils.validatePageNumberAndSize;


@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public AuthorResponse addAuthor( AuthorRequest authorRequest ) {
        Author author = new Author();

        author.setFirstName( authorRequest.getFirstName() );
        author.setLastName( authorRequest.getLastName() );
        author.setDateOfBirth( authorRequest.getDateOfBirth() );

        Author newAuthor = authorRepository.save( author );

        AuthorResponse authorResponse = new AuthorResponse();

        authorResponse.setId( newAuthor.getId() );
        authorResponse.setFirstName( newAuthor.getFirstName() );
        authorResponse.setLastName( newAuthor.getLastName() );
        authorResponse.setDateOfBirth( newAuthor.getDateOfBirth() );

        return authorResponse;
    }


    @Override
    public PagedResponse<Author> getAllAuthors( PageFilterRequest pageFilterRequest ) {
        validatePageNumberAndSize( pageFilterRequest.getPage(), pageFilterRequest.getSize() );

        Pageable pageable = PageRequest.of( pageFilterRequest.getPage(), pageFilterRequest.getSize(),
                pageFilterRequest.getSortDirection(), pageFilterRequest.getSortingAttribute() );

        Page<Author> authors = authorRepository.findAll( pageable );

        List<Author> content = authors.getNumberOfElements() == 0 ? Collections.emptyList() : authors.getContent();

        return new PagedResponse<>( content, authors.getNumber(), authors.getSize(), authors.getTotalElements(),
                authors.getTotalPages(), authors.isLast() );

    }


    @Override
    public Author getAuthor( Long id ) {
        return authorRepository.findById( id ).orElseThrow( () -> new ResourceNotFoundException( AUTHOR, ID, id ) );
    }


    @Override
    public Author updateAuthor( Long id, AuthorRequest newAuthorRequest ) {
        Author author = authorRepository.findById( id )
                .orElseThrow( () -> new ResourceNotFoundException( AUTHOR, ID, id ) );

        author.setLastName( newAuthorRequest.getLastName() );
        author.setFirstName( newAuthorRequest.getFirstName() );
        author.setDateOfBirth( newAuthorRequest.getDateOfBirth() );
        return authorRepository.save( author );
    }


    @Override
    public ApiResponse deleteAuthor( Long id, UserPrincipal currentUser ) {
        authorRepository.deleteById( id );
        return new ApiResponse( Boolean.TRUE, "You successfully deleted author" );
    }
}
