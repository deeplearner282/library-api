package com.bei.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bei.libary.model.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
