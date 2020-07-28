package com.carbonit.restapikata.model;

import com.carbonit.restapikata.persistence.Book;

public class BookMapper {

    public static BookDTO entityToDto(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn()
        );
    }

    public static Book dtoToEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.getId(),
                bookDTO.getTitle(),
                bookDTO.getAuthor(),
                bookDTO.getIsbn());
    }

    public static Book newDtoToEntity(NewBookDTO newBookDTO) {
        return new Book(
                newBookDTO.getTitle(),
                newBookDTO.getAuthor(),
                newBookDTO.getIsbn());
    }



}
