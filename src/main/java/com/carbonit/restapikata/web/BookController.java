package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.BookDTO;
import com.carbonit.restapikata.model.NewBookDTO;
import com.carbonit.restapikata.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Collection<BookDTO> getAllBooks() {
        log.info("GET all books ");
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") UUID id) {
        log.info("GET book, id: " + id);
        return bookService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody NewBookDTO newBook) {
        log.info("CREATE new book : \"" + newBook.getTitle() + "\"");
        return bookService.create(newBook);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@RequestBody BookDTO newBook, @PathVariable("id") UUID id) {
        log.info("UPDATE book, id: " + id);
        if (!id.equals(newBook.getId())) {
            log.warn("UPDATE book exception, book id: " + newBook.getId() + ", param id: " + id.toString());
            throw new IdMismatchException("id from path and bookDto differ");
        }
        return bookService.update(id, newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        log.info("DELETE book, id: " + id);
        bookService.delete(id);
    }
}
