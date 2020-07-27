package com.carbonit.restapikata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestApiKataApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiKataApplication.class, args);
    }
/*
    @Bean
    CommandLineRunner test(StudentRepository repository) {
        return (String... args) -> {
            Student student1 = new Student("Jacques", 101.15f);
            Student student2 = new Student("Julie", 102.02f);

            // Act
            repository.save(student1);
            repository.save(student2);
            var students = StreamSupport.stream(repository.findAll().spliterator(), false)
                    //.map(Student::getId)
                    .collect(Collectors.toList());
            //students.forEach(s -> System.out.format("student : %s, %s, %tc, %tc", s.getId(), s.getName(), s.getCreationDate(), s.getUpdateDate() ));
        };
    }
 */

}
