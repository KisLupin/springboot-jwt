package com.lupin.security.repository;

import com.lupin.security.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Books,Integer> {
    Books findByName(String name);
    List<Books> findByCount(int count);
}
