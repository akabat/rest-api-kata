package com.carbonit.restapikata.domain;

import java.util.Collection;
import java.util.UUID;

public interface IReaderRepository {

    // Create
    Reader create(Reader reader);

    // Read
    Reader findById(UUID readerId);

    Collection<Reader> findAll();
    Collection<Reader> findAllWithBooks();
    Collection<Reader> findByFirstName(String firstName);
    Collection<Reader> findByLastName(String lastName);

    // Update
    Reader update(Reader book);

    // Delete
    void delete(Reader book);
}
