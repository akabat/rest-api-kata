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
    public Collection<ReaderDTO> getAllReaders() {
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    public ReaderDTO getReader(@PathVariable("id") UUID id) {
        return readerService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createReader(@RequestBody NewReaderDTO newReader) {
        return readerService.create(newReader);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createReader(@RequestBody ReaderDTO newReader, @PathVariable("id") UUID id) {
        if (!id.equals(newReader.getId())) {
            throw new IllegalArgumentException("id from path and bookdto differs");
        }
        return readerService.update(id, newReader);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID id) {
        readerService.delete(id);
    }

}
