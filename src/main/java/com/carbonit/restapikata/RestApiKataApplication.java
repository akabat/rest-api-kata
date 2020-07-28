package com.carbonit.restapikata;

import com.carbonit.restapikata.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.StreamSupport;

@SpringBootApplication
public class RestApiKataApplication implements CommandLineRunner {

    private final IReaderRepository readersRepository;
    private final ReaderService readerService;

    private final IBookRepository booksRepository;
    private final BookService bookService;

    private static final Logger log = LoggerFactory.getLogger(RestApiKataApplication.class);

    public RestApiKataApplication(IReaderRepository readersRepository, ReaderService readerService,
                                  IBookRepository booksRepository, BookService bookService) {
        this.readersRepository = readersRepository;
        this.readerService = readerService;
        this.booksRepository = booksRepository;
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApiKataApplication.class, args);
        log.warn("Logger check...");
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Adding few readers for testing purpose...");

        // Arrange
        var reader1 = new Reader("Jacques", "Milonga", 44, "Engineer");
        var reader2 = new Reader("Julie", "Ivanova", 34, "Postgraduate");

        readersRepository.create(reader1);
        readersRepository.create(reader2);

        var book1 = new Book("Akwarium", "Suvorov", "IJI-IJMLKJI-432");
        var book2 = new Book("Transatlantyk", "Gombrowicz", "ABC-KJK-332");
        var book3 = new Book("Raisin de la colere", "John Steinbeck", "CKFDQ-342-332");
        var book4 = new Book("Bible", "Jean", "REG-35-332");

        booksRepository.create(book1);
        booksRepository.create(book2);
        booksRepository.create(book3);
        booksRepository.create(book4);

        readerService.addBookToReadersLectureList(reader1, book1);
        readerService.addBookToReadersLectureList(reader1, book2);
        readerService.addBookToReadersLectureList(reader1, book3);

        readerService.addBookToReadersLectureList(reader2, book2);
        readerService.addBookToReadersLectureList(reader2, book3);
        readerService.addBookToReadersLectureList(reader2, book4);

        StreamSupport.stream(booksRepository.findAll().spliterator(), false)
                .flatMap(b -> b.getReaders().stream())
                .forEach(r -> System.out.println(String.format("book: %s %s %tc %tc",
                        r.getFirstName(), r.getLastName(), r.getCreationDate(), r.getUpdateDate())));
    }
}
