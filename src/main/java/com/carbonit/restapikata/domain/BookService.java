package com.carbonit.restapikata.domain;

import javax.transaction.Transactional;
import java.util.Collection;

public class BookService {

    private final IBookRepository bookRepository;
    private final IReaderRepository readerRepository;

    public BookService(IBookRepository bookRepository, IReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public void attachReaderToBook(Book book, Reader reader) {
        if(book.getId() == null)
            book = bookRepository.create(book);
        if(reader.getId() == null)
            reader = readerRepository.create(reader);
        book.addReader(reader);
        bookRepository.update(book);
    }

    Collection<Book> findAllWithReaders() {
        return bookRepository.findAllWithReaders();
    }


}
