package com.carbonit.restapikata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data @AllArgsConstructor
public class BookDTO {

    private final UUID id;
    private final String title;
    private final String author;
    private final String isbn;

}
