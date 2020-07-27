package com.carbonit.restapikata.domain;

import java.util.Collection;
import java.util.UUID;

public interface BookRepository {
    // Create
    Book create(Book book);

    // Read
    Book findById(UUID bookId);
    Collection<Book> findByTitle(String title);
    Collection<Book> findByAuthor(String author);
    Book findByIsbn(String isbn);

    // Update
    Book update(Book book);

    // Delete
    Book delete(Book book);
}
