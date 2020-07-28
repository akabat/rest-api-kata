package com.carbonit.restapikata;

import com.carbonit.restapikata.persistence.Book;
import com.carbonit.restapikata.persistence.BookRepository;
import com.carbonit.restapikata.persistence.Reader;
import com.carbonit.restapikata.persistence.ReaderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public DataSeeder(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var books = Arrays.asList(
                new Book("Raisins de la Colere", "John Steinbeck", "iui-4342-ere"),
                new Book("Docteur Jivago", "Boris Pasternak", "ttt-455-eTTe"),
                new Book("Messire Thadée", "Adam Mickiewicz", "iui-4342-ere"));
        bookRepository.saveAll(books);

        var readers = Arrays.asList(
                new Reader("Vincent", "Vieira", 25, "Engineer"),
                new Reader("Maxim", "Mader", 38, "Engineer"),
                new Reader("Andrzej", "Kabat", 39, "Engineer"));
        readerRepository.saveAll(readers);
    }
}
