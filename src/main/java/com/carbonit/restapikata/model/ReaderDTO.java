package com.carbonit.restapikata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data @AllArgsConstructor
public class ReaderDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String educationLevel;
    //private Set<Book> books = new HashSet<>();

}
