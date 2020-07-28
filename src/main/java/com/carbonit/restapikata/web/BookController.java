package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.BookDTO;
import com.carbonit.restapikata.model.NewBookDTO;
import com.carbonit.restapikata.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<BookDTO> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") UUID id) {
        return bookService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody NewBookDTO newBook) {
        return bookService.create(newBook);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody BookDTO newBook, @PathVariable("id") UUID id) {
        if (!id.equals(newBook.getId())) {
            throw new IllegalArgumentException("id from path and bookdto differs");
        }
        return bookService.update(id, newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        bookService.delete(id);
    }
}
