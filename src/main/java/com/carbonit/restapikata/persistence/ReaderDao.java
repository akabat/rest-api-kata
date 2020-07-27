package com.carbonit.restapikata.persistence;

import com.carbonit.restapikata.domain.Reader;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class ReaderDao implements com.carbonit.restapikata.domain.ReaderRepository {

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
    public Collection<Reader> findByFirstName(String firstName) {

        return null;
    }

    @Override
    public Collection<Reader> findByLastName(String lastName) {
        return null;
    }

    @Override
    public Reader update(Reader book) {
        return null;
    }

    @Override
    public Reader delete(Reader book) {
        return null;
    }
}
