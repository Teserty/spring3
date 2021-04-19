package com.teserty.spring3.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String categoryName;
    private String parentCategoryName;
}
