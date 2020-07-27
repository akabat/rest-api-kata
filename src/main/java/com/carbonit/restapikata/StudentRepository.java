package com.carbonit.restapikata;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends PagingAndSortingRepository<Student, UUID> {

}
