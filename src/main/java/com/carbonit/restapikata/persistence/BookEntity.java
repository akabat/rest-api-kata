package com.carbonit.restapikata.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "book")
@Getter @Setter @NoArgsConstructor
public class BookEntity implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.carbonit.restapikata.persistence.PostgreSQLUUIDGenerationStrategy"
            )
    )
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @ManyToMany//(cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "lecture",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reader_id", referencedColumnName = "id"))
    private Set<ReaderEntity> readers = new HashSet<>();

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;

    public BookEntity(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    /*
    public void addReader(ReaderEntity reader) {
        if(readers == null)
            readers = new HashSet<>();

        if(!readers.contains(reader)) {
            readers.add(reader);
            reader.addLecture(this);
        }
    }

    public void removeReader(ReaderEntity reader) {
        if(readers.contains(reader)) {
            readers.remove(reader);
            reader.removeLecture(this);
        }
    }
     */
}
