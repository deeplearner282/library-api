package com.bei.libary.service;

import com.bei.libary.model.Author;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.AuthorResponse;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.payload.AuthorRequest;


public interface AuthorService {
    AuthorResponse addAuthor( AuthorRequest authorRequest );


    PagedResponse<Author> getAllAuthors( PageFilterRequest pageFilterRequest);


    Author getAuthor( Long id );


    Author updateAuthor( Long id, AuthorRequest newAuthorRequest );


    ApiResponse deleteAuthor( Long id, UserPrincipal currentUser );
}
