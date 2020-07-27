package com.carbonit.restapikata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void whenCalledSave_thenStudentGetsPersisted() {
        // Arrange
        var student = new Student("Kinga", 103.45f);

        // Act
        student = studentRepository.save(student);
        var persistentStudent = studentRepository.findById(student.getId()).get();

        // Assert
        assertEquals(persistentStudent.getId(), student.getId());
    }

    @Test
    void whenSavingMoreStudents_thenAllGetPersisted() {
        // Arrange
        Student student1 = new Student("Jacques", 101.15f);
        Student student2 = new Student("Julie", 102.02f);

        // Act
        studentRepository.save(student1);
        studentRepository.save(student2);
        var students = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(Student::getId)
                .collect(Collectors.toList());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(2, students.size()),
                () -> assertTrue(students.contains(student1.getId())),
                () -> assertTrue(students.contains(student2.getId())));

    }

}