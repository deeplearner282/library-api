package com.bei.libary.payload;

import javax.swing.SortOrder;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestParam;

import com.bei.libary.utils.AppConstants;

import lombok.Builder;
import lombok.Data;


@Data
public class PageFilterRequest {
    private Integer page = AppConstants.DEFAULT_PAGE_NUMBER;

    private Integer size = AppConstants.DEFAULT_PAGE_SIZE;

    private String sortingAttribute;

    private Sort.Direction sortDirection = Sort.Direction.ASC;
}
