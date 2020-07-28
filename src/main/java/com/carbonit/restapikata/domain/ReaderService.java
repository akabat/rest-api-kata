package com.carbonit.restapikata.domain;

import java.util.Collection;

public class ReaderService {

    private final IReaderRepository readerRepository;
    private final IBookRepository bookRepository;

    public ReaderService(IReaderRepository readerRepository, IBookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    public void addBookToReadersLectureList(Reader reader, Book book) {
        if(reader.getId() == null)
            reader = readerRepository.create(reader);
        if(book.getId() == null)
            book = bookRepository.create(book);
        reader.addLecture(book);
        readerRepository.update(reader);
    }

    Collection<Reader> findAllWithBooks() {
        return readerRepository.findAllWithBooks();
    }

}
