package com.carbonit.restapikata.model;

import com.carbonit.restapikata.persistence.Reader;

public class ReaderMapper {

    public static ReaderDTO entityToDto(Reader reader) {
        return new ReaderDTO(reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getAge(),
                reader.getEducationLevel());
    }

    public static Reader dtoToEntity(ReaderDTO readerDTO) {
        return new Reader(readerDTO.getId(),
                readerDTO.getFirstName(),
                readerDTO.getLastName(),
                readerDTO.getAge(),
                readerDTO.getEducationLevel());
    }

    public static Reader newDtoToEntity(NewReaderDTO newReaderDTO) {
        return new Reader(newReaderDTO.getFirstName(),
                newReaderDTO.getLastName(),
                newReaderDTO.getAge(),
                newReaderDTO.getEducationLevel());
    }
}
