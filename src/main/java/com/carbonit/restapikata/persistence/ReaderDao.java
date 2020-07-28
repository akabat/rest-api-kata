package com.carbonit.restapikata.persistence;

import com.carbonit.restapikata.domain.IReaderRepository;
import com.carbonit.restapikata.domain.Reader;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ReaderDao implements IReaderRepository {

    private final ReaderRepository readerRepository;

    public ReaderDao(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public Reader create(Reader reader) {
        var entityReader = readerRepository.save(ReaderMapper.domainToEntity(reader));
        return ReaderMapper.entityToDomain(entityReader);
    }

    @Override
    public Reader findById(UUID readerId) {
        var entityReader = readerRepository.findById(readerId);
        return entityReader.isPresent()? ReaderMapper.entityToDomain(entityReader.get()) : null;
    }

    @Override
    public Collection<Reader> findAll() {
        var entityReaders = readerRepository.findAll();
        return StreamSupport.stream(entityReaders.spliterator(), false)
                .map(ReaderMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Collection<Reader> findAllWithBooks() {
        var entityReaders = readerRepository.findAll();
        return StreamSupport.stream(entityReaders.spliterator(), false)
                .map(ReaderMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Reader> findByFirstName(String firstName) {
        var entities = readerRepository.findAllByFirstName(firstName);
        return StreamSupport.stream(entities.spliterator(), false)
                .map(ReaderMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<Reader> findByLastName(String lastName) {
        var entities = readerRepository.findAllByLastName(lastName);
        return StreamSupport.stream(entities.spliterator(), false)
                .map(ReaderMapper::entityToDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Reader update(Reader book) {
        return create(book);
    }

    @Override
    public void delete(Reader book) {
        readerRepository.deleteById(book.getId());
    }
}
