package com.carbonit.restapikata.domain;

import java.util.Collection;
import java.util.UUID;

public interface IBookRepository {
    // Create
    Book create(Book book);

    // Read
    Book findById(UUID bookId);
    Book findByIsbn(String isbn);

    Collection<Book> findAll();
    Collection<Book> findAllWithReaders();
    Collection<Book> findByTitle(String title);
    Collection<Book> findByAuthor(String author);

    // Update
    Book update(Book book);

    // Delete
    void delete(Book book);
}
