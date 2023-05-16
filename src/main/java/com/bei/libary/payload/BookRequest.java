package com.bei.libary.payload;

import java.math.BigDecimal;
import java.time.Instant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
public class BookRequest {
    @NotBlank
    private String title;

    @NotNull
    private BigDecimal price;

    @NotNull
    private Instant publicationDate;

    @NotNull
    private Long authorId;

}
