package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.NewReaderDTO;
import com.carbonit.restapikata.model.ReaderDTO;
import com.carbonit.restapikata.service.ReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public Collection<ReaderDTO> getAllBooks() {
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    public ReaderDTO getBook(@PathVariable("id") UUID id) {
        return readerService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createBook(@RequestBody NewReaderDTO newBook) {
        return readerService.create(newBook);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createBook(@RequestBody ReaderDTO newBook, @PathVariable("id") UUID id) {
        if (!id.equals(newBook.getId())) {
            throw new IllegalArgumentException("id from path and bookdto differs");
        }
        return readerService.update(id, newBook);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        readerService.delete(id);
    }

}
