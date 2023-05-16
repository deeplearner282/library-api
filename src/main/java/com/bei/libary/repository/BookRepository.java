package com.bei.libary.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bei.libary.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByAuthorId(Long authorId, Pageable pageable );
}
