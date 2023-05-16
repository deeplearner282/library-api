package com.bei.libary.service;

import com.bei.libary.model.Book;
import com.bei.libary.payload.ApiResponse;
import com.bei.libary.payload.PageFilterRequest;
import com.bei.libary.payload.PagedResponse;
import com.bei.libary.security.UserPrincipal;
import com.bei.libary.payload.BookRequest;
import com.bei.libary.payload.BookResponse;


public interface BookService {
    BookResponse addBook( BookRequest bookRequest );


    PagedResponse<Book> getAllBooks(PageFilterRequest pageFilterRequest);


    Book getBook( Long id );


    Book updateBook( Long id, BookRequest newBookRequest );


    ApiResponse deleteBook( Long id, UserPrincipal currentUser );


    PagedResponse<Book> getAllBooksByAuthorId( Long authorId, PageFilterRequest pageFilterRequest );
}
