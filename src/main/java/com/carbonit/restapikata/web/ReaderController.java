package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.NewReaderDTO;
import com.carbonit.restapikata.model.ReaderDTO;
import com.carbonit.restapikata.service.ReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    private static final Logger log = LoggerFactory.getLogger(ReaderController.class);

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    public Collection<ReaderDTO> getAllReaders() {
        log.info("GET all readers");
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    public ReaderDTO getReader(@PathVariable("id") UUID id) {
        log.info("GET reader, id: " + id);
        return readerService.findOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderDTO createReader(@RequestBody NewReaderDTO newReader) {
        log.info(String.format("CREATE new reader: %s %s, %d y.o.",
                newReader.getFirstName(),
                newReader.getLastName(),
                newReader.getAge()));
        return readerService.create(newReader);
    }

    @PutMapping("/{id}")
    public ReaderDTO createReader(@RequestBody ReaderDTO reader, @PathVariable("id") UUID id) {
        log.info(String.format("UPDATE reader, id: %s", id.toString()));
        if (!id.equals(reader.getId())) {
            log.warn(String.format("UPDATE reader exception, reader id: %s, param id %s",
                    reader.getId(), id.toString()));
            throw new IdMismatchException("id from path and bookDto differs");
        }
        return readerService.update(id, reader);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        log.info(String.format("DELETE reader, id: %s", id.toString()));
        readerService.delete(id);
    }

}
