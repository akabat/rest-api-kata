package com.carbonit.restapikata.domain;

import java.util.UUID;

public class ReaderService {

    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public ReaderService(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    public void addBookToReadersLectureList(UUID readerId, UUID bookId) {
        var reader = readerRepository.findById(readerId);
        var book = bookRepository.findById(bookId);
        reader.addLecture(book);
        readerRepository.update(reader);
    }

}
