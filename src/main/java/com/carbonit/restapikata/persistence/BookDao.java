package com.carbonit.restapikata.persistence;

import com.carbonit.restapikata.domain.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class BookDao implements com.carbonit.restapikata.domain.BookRepository {

    private final BookRepository bookRepository;

    public BookDao(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return null;
    }

    @Override
    public Book findById(UUID bookId) {
        return null;
    }

    @Override
    public Collection<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public Book findByIsbn(String isbn) {
        return null;
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public Book delete(Book book) {
        return null;
    }
}
