package com.carbonit.restapikata.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookDoesNotExistException extends ResponseStatusException {
    public BookDoesNotExistException() {
        super(HttpStatus.NOT_FOUND);
    }
}
