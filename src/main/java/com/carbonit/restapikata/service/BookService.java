package com.carbonit.restapikata.service;

import com.carbonit.restapikata.model.BookDTO;
import com.carbonit.restapikata.model.BookMapper;
import com.carbonit.restapikata.model.NewBookDTO;
import com.carbonit.restapikata.persistence.BookDoesNotExistException;
import com.carbonit.restapikata.persistence.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Collection<BookDTO> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
                .map(BookMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    public BookDTO findOne(UUID id) {
        return bookRepository.findById(id).map(BookMapper::entityToDto).orElseThrow(BookDoesNotExistException::new);
    }

    @Transactional
    public BookDTO create(NewBookDTO newBook) {
        return BookMapper.entityToDto(
                bookRepository.save(
                        BookMapper.newBookDtoToEntity(newBook)));
    }

    @Transactional
    public BookDTO update(UUID id, BookDTO book) {
        return BookMapper.entityToDto(
            bookRepository.save(
                    BookMapper.bookDtoToEntity(book)));
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }
}
