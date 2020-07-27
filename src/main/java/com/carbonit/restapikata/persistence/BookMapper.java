package com.carbonit.restapikata.persistence;

import com.carbonit.restapikata.domain.Book;

import java.util.stream.Collectors;

public class BookMapper {

    public static BookEntity domainToEntity(Book domainBook) {
        var entityBook = new BookEntity();
        entityBook.setId(domainBook.getId());
        entityBook.setAuthor(domainBook.getAuthor());
        entityBook.setTitle(domainBook.getTitle());
        entityBook.setIsbn(domainBook.getIsbn());
        entityBook.getReaders().addAll(domainBook.getReaders().stream()
                .map(ReaderMapper::domainToEntity)
                .collect(Collectors.toSet()));
        return entityBook;
    }

    public static Book entityToDomain(BookEntity entityBook) {
        return new Book(
                entityBook.getId(),
                entityBook.getTitle(),
                entityBook.getAuthor(),
                entityBook.getIsbn(),
                entityBook.getCreationDate(),
                entityBook.getUpdateDate());
    }
}
