package com.carbonit.restapikata;

import com.carbonit.restapikata.domain.BookService;
import com.carbonit.restapikata.domain.IBookRepository;
import com.carbonit.restapikata.domain.IReaderRepository;
import com.carbonit.restapikata.domain.ReaderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

    @Bean
    public ReaderService getReaderService(IReaderRepository readerRepository,
                                          IBookRepository bookRepository) {
        return new ReaderService(readerRepository, bookRepository);
    }

    @Bean
    public BookService getBookService(IBookRepository bookRepository,
                                      IReaderRepository readerRepository) {
        return new BookService(bookRepository, readerRepository);
    }
}
