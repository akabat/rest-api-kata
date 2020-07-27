package com.carbonit.restapikata.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "reader")
@Getter @Setter @NoArgsConstructor
public class ReaderEntity implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.carbonit.restapikata.persistence.PostgreSQLUUIDGenerationStrategy"
            )
    )
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "education_level")
    private String educationLevel;

    @ManyToMany(mappedBy = "readers"/*, cascade = {CascadeType.MERGE}*/)
    private Set<BookEntity> books = new HashSet<>();

    //@CreatedDate : Auditing does not work : empty field
    @CreationTimestamp
    private Date creationDate;

    //@LastModifiedDate : Auditing does not work : empty field
    @UpdateTimestamp
    private Date updateDate;

    public ReaderEntity(String firstName, String lastName, Integer age, String educationLevel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.educationLevel = educationLevel;
    }

    /*
    public void addLecture(BookEntity book) {
        if(books == null)
            books = new HashSet<>();

        if(!books.contains(book)) {
            books.add(book);
            book.addReader(this);
        }
    }

    public void removeLecture(BookEntity book) {
        if(books.contains(book)) {
            books.remove(book);
            book.removeReader(this);
        }
    }
     */
}
