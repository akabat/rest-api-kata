package com.carbonit.restapikata.model;

import com.carbonit.restapikata.persistence.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data @AllArgsConstructor
public class ReaderDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String educationLevel;
    private Set<Book> books = new HashSet<>();

}
