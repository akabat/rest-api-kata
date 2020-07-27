package com.carbonit.restapikata.domain;

import java.util.Collection;
import java.util.UUID;

public interface ReaderRepository {

    // Create
    Reader create(Reader reader);

    // Read
    Reader findById(UUID readerId);

    Collection<Reader> findByFirstName(String firstName);
    Collection<Reader> findByLastName(String lastName);

    // Update
    Reader update(Reader book);

    // Delete
    Reader delete(Reader book);
}
