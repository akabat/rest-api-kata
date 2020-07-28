package com.carbonit.restapikata.service;

import com.carbonit.restapikata.model.NewReaderDTO;
import com.carbonit.restapikata.model.ReaderDTO;
import com.carbonit.restapikata.model.ReaderMapper;
import com.carbonit.restapikata.web.ReaderDoesNotExistException;
import com.carbonit.restapikata.persistence.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReaderService {

    public final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public Collection<ReaderDTO> findAll() {
        return StreamSupport.stream(readerRepository.findAll().spliterator(), false)
                .map(ReaderMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    public ReaderDTO findOne(UUID id) {
        return readerRepository.findById(id).map(ReaderMapper::entityToDto)
                .orElseThrow(ReaderDoesNotExistException::new);
    }

    public ReaderDTO create(NewReaderDTO newBook) {
        return ReaderMapper.entityToDto(
                readerRepository.save(
                        ReaderMapper.newDtoToEntity(newBook)
                ));
    }

    public ReaderDTO update(UUID id, ReaderDTO bookDto) {
        return ReaderMapper.entityToDto(
                readerRepository.save(
                        ReaderMapper.dtoToEntity(bookDto)
                ));
    }

    public void delete(UUID id) {
        readerRepository.deleteById(id);
    }
}
