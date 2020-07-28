package com.carbonit.restapikata.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class NewReaderDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private String educationLevel;
}
