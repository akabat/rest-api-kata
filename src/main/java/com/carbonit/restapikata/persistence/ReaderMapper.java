package com.carbonit.restapikata.persistence;

import com.carbonit.restapikata.domain.Reader;

import java.util.stream.Collectors;

public class ReaderMapper {

    public static ReaderEntity domainToEntity(Reader domainReader) {
        var entityReader = new ReaderEntity();
        entityReader.setId(domainReader.getId());
        entityReader.setFirstName(domainReader.getFirstName());
        entityReader.setLastName(domainReader.getLastName());
        entityReader.setAge(domainReader.getAge());
        entityReader.getBooks().addAll(domainReader.getBooks().stream()
                .map(BookMapper::domainToEntity)
                .collect(Collectors.toSet()));
        return entityReader;
    }

    public static Reader entityToDomain(ReaderEntity entityReader) {
        return new Reader(
                entityReader.getId(),
                entityReader.getFirstName(),
                entityReader.getLastName(),
                entityReader.getAge(),
                entityReader.getEducationLevel(),
                entityReader.getCreationDate(),
                entityReader.getUpdateDate());
    }

}
