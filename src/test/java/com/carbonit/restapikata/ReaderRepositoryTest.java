package com.carbonit.restapikata;

import com.carbonit.restapikata.persistence.Reader;
import com.carbonit.restapikata.persistence.ReaderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReaderRepositoryTest {

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void whenCalledSave_thenStudentGetsPersisted() {
        // Arrange
        var student = new Reader("Milena", "Knight", 44, "PhD");

        // Act
        student = readerRepository.save(student);
        var persistentStudent = readerRepository.findById(student.getId()).get();

        // Assert
        assertEquals(persistentStudent.getId(), student.getId());
    }

    @Test
    void whenSavingMoreStudents_thenAllGetPersisted() {
        // Arrange
        Reader reader1 = new Reader("Jacques", "Chirac", 92, "Higher");
        Reader reader2 = new Reader("Bill", "Bull", 18, "Undergraduate");

        // Act
        readerRepository.save(reader1);
        readerRepository.save(reader2);
        var students = StreamSupport.stream(readerRepository.findAll().spliterator(), false)
                .map(Reader::getId)
                .collect(Collectors.toList());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(2, students.size()),
                () -> assertTrue(students.contains(reader1.getId())),
                () -> assertTrue(students.contains(reader2.getId())));

    }

}