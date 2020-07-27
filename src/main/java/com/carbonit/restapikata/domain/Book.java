package com.carbonit.restapikata.domain;

import java.util.*;

public class Book {

    private final UUID id;
    private final String title;
    private final String author;
    private final String isbn;
    private final Set<Reader> readers = new HashSet<>();
    private final Date creationDate;
    private final Date updateDate;

    public void addReader(Reader reader) {
        if(!readers.contains(reader)) {
            readers.add(reader);
            reader.addLecture(this);
        }
    }

    public void removeReader(Reader reader) {
        if(readers.contains(reader)) {
            readers.remove(reader);
            reader.removeLecture(this);
        }
    }

    public Book(UUID id, String title, String author, String isbn, Date creationDate, Date updateDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
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
                Objects.equals(readers, book.readers) &&
                Objects.equals(creationDate, book.creationDate) &&
                Objects.equals(updateDate, book.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, readers, creationDate, updateDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", readers=" + readers +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
