package com.lupin.security.repository;

import com.lupin.security.model.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Books,Integer> {
    @Query(nativeQuery = true,
            value = "select * from book where name = :name"
    )
    @Transactional
    Optional<Books> findByName(@Param("name") String name);
}
