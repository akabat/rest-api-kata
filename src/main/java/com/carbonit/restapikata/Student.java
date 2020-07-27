package com.carbonit.restapikata;

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
import java.util.UUID;

@Entity
@Table(name = "student")
@Getter @Setter @NoArgsConstructor
public class Student implements Serializable {

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
                    value = "com.carbonit.restapikata.PostgreSQLUUIDGenerationStrategy"
            )
    )
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "iq")
    private Float iq;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;

    public Student(String name, Float iq) {
        this.name = name;
        this.iq = iq;
    }
}
