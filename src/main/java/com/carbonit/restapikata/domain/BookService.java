package com.carbonit.restapikata.domain;

import java.util.UUID;

public class BookService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public BookService(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void attachReaderToBook(UUID bookId, UUID readerId) {
        var book = bookRepository.findById(bookId);
        var reader = readerRepository.findById(readerId);
        book.addReader(reader);
        bookRepository.update(book);
    }
}
