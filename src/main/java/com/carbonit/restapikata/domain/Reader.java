package com.carbonit.restapikata.domain;

import java.io.Serializable;
import java.util.*;

public class Reader implements Serializable {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String educationLevel;
    private final Set<Book> books = new HashSet<>();
    private final Date creationDate;
    private final Date updateDate;

    public void addLecture(Book book) {
        if (!books.contains(book)) {
            books.add(book);
            book.addReader(this);
        }
    }

    public void removeLecture(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            book.removeReader(this);
        }
    }

    public Reader(UUID id, String firstName, String lastName, Integer age, String educationLevel, Date creationDate, Date updateDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.educationLevel = educationLevel;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public Set<Book> getBooks() {
        return books;
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
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id) &&
                Objects.equals(firstName, reader.firstName) &&
                Objects.equals(lastName, reader.lastName) &&
                Objects.equals(age, reader.age) &&
                Objects.equals(educationLevel, reader.educationLevel) &&
                Objects.equals(books, reader.books) &&
                Objects.equals(creationDate, reader.creationDate) &&
                Objects.equals(updateDate, reader.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, educationLevel, books, creationDate, updateDate);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", educationLevel='" + educationLevel + '\'' +
                ", books=" + books +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                '}';
    }
}