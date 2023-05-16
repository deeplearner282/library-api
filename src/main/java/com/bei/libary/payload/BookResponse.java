package com.bei.libary.payload;

import java.math.BigDecimal;
import java.time.Instant;

import com.bei.libary.model.Author;

import lombok.Data;


@Data
public class BookResponse {
    private Long id;

    private String title;

    private BigDecimal price;

    private Instant publicationDate;

    private Long authorId;
}
