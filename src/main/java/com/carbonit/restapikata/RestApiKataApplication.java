package com.carbonit.restapikata;

import com.carbonit.restapikata.domain.BookService;
import com.carbonit.restapikata.domain.ReaderService;
import com.carbonit.restapikata.persistence.BookEntity;
import com.carbonit.restapikata.persistence.BookRepository;
import com.carbonit.restapikata.persistence.ReaderEntity;
import com.carbonit.restapikata.persistence.ReaderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.transaction.Transactional;
import java.util.stream.StreamSupport;

@SpringBootApplication
public class RestApiKataApplication {

    private static final Logger log = LoggerFactory.getLogger(RestApiKataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApiKataApplication.class, args);
        log.warn("Logger check...");
    }

    @Bean
    public ReaderService getReaderService(com.carbonit.restapikata.domain.ReaderRepository readerRepository,
                                   com.carbonit.restapikata.domain.BookRepository bookRepository) {
        return new ReaderService(readerRepository, bookRepository);
    }

    @Bean
    public BookService getBookService(com.carbonit.restapikata.domain.BookRepository bookRepository,
                                      com.carbonit.restapikata.domain.ReaderRepository readerRepository) {
        return new BookService(bookRepository, readerRepository);
    }

    @Bean
    @Transactional
    CommandLineRunner test(ReaderRepository readersRepository, BookRepository booksRepository) {
        return (String... args) -> {
            log.info("Adding few readers for testing purpose...");

            // Arrange
            var reader1 = new ReaderEntity("Jacques", "Milonga", 44, "Engineer");
            var reader2 = new ReaderEntity("Julie", "Ivanova", 34, "Postgraduate");

            readersRepository.save(reader1);
            readersRepository.save(reader2);

            var book1 = new BookEntity("Akwarium", "Suvorov", "IJI-IJMLKJI-432");
            var book2 = new BookEntity("Transatlantyk", "Gombrowicz", "ABC-KJK-332");
            var book3 = new BookEntity("Raisin de la colere", "John Steinbeck", "CKFDQ-342-332");
            var book4 = new BookEntity("Bible", "Jean", "REG-35-332");

            booksRepository.save(book1);
            booksRepository.save(book2);
            booksRepository.save(book3);
            booksRepository.save(book4);

            /*
            reader1.addLecture(book1);
            reader1.addLecture(book2);
            reader1.addLecture(book3);

            reader2.addLecture(book2);
            reader2.addLecture(book3);
            reader2.addLecture(book4);
            */

            readersRepository.save(reader1);
            readersRepository.save(reader2);

            StreamSupport.stream(booksRepository.findAll().spliterator(), false)
                    .flatMap(b -> b.getReaders().stream())
                    .forEach(r -> System.out.println(String.format("book: %s %s %tc %tc",
                    r.getFirstName(), r.getLastName(), r.getCreationDate(), r.getUpdateDate())));
        };
    }
}
