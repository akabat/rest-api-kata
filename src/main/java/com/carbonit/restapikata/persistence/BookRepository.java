package com.carbonit.restapikata.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.UUID;

@Repository
public interface BookRepository extends PagingAndSortingRepository<BookEntity, UUID> {

    Collection<BookEntity> findAllByTitle(String title);
    Collection<BookEntity> findAllByAuthor(String author);
    Collection<BookEntity> findAllByIsbn(String isbn);
}
