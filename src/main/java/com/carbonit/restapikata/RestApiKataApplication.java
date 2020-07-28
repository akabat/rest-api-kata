package com.carbonit.restapikata;

import com.carbonit.restapikata.persistence.Book;
import com.carbonit.restapikata.persistence.BookRepository;
import com.carbonit.restapikata.persistence.Reader;
import com.carbonit.restapikata.persistence.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.StreamSupport;

@SpringBootApplication
public class RestApiKataApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApiKataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApiKataApplication.class, args);
        log.warn("Logger check...");
    }

    @Bean
    //@Transactional
    CommandLineRunner test(ReaderRepository readersRepository, BookRepository booksRepository) {
        return (String... args) -> {
            log.info("Adding few readers for testing purpose...");

            // Arrange
            var reader1 = new Reader("Jacques", "Milonga", 44, "Engineer");
            var reader2 = new Reader("Julie", "Ivanova", 34, "Postgraduate");

            readersRepository.save(reader1);
            readersRepository.save(reader2);

            var book1 = new Book("Akwarium", "Suvorov", "IJI-IJMLKJI-432");
            var book2 = new Book("Transatlantyk", "Gombrowicz", "ABC-KJK-332");
            var book3 = new Book("Raisin de la colere", "John Steinbeck", "CKFDQ-342-332");
            var book4 = new Book("Bible", "Jean", "REG-35-332");

            booksRepository.save(book1);
            booksRepository.save(book2);
            booksRepository.save(book3);
            booksRepository.save(book4);

            reader1.addLecture(book1);
            reader1.addLecture(book2);
            reader1.addLecture(book3);

            reader2.addLecture(book2);
            reader2.addLecture(book3);
            reader2.addLecture(book4);

            readersRepository.save(reader1);
            readersRepository.save(reader2);

            StreamSupport.stream(booksRepository.findAll().spliterator(), false)
                    .flatMap(b -> b.getReaders().stream())
                    .forEach(r -> System.out.println(String.format("book: %s %s %tc %tc",
                    r.getFirstName(), r.getLastName(), r.getCreationDate(), r.getUpdateDate())));
        };
    }
}
