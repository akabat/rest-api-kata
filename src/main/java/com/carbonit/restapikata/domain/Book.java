package com.carbonit.restapikata.domain;

import java.util.*;

public class Book {

    private final UUID id;
    private final String title;
    private final String author;
    private final String isbn;
    private final Set<Reader> readers = new HashSet<>();

    void addReader(Reader reader) {
        if(!readers.contains(reader)) {
            readers.add(reader);
            reader.addLecture(this);
        }
    }

    void removeReader(Reader reader) {
        if(readers.contains(reader)) {
            readers.remove(reader);
            reader.removeLecture(this);
        }
    }

    public Book(String title, String author, String isbn) {
        this.id = null;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(UUID id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Collection<Reader> getReaders() {
        return Collections.unmodifiableCollection(readers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(readers, book.readers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, readers);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", readers=" + readers +
                '}';
    }
}
