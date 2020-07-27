package com.carbonit.restapikata;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReaderRepository extends PagingAndSortingRepository<Reader, UUID> {

}
