package com.carbonit.restapikata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class NewBookDTO {
    private final String title;
    private final String author;
    private final String isbn;
}
