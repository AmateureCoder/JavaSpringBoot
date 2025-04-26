package com.propane.libmanv1.identity.repository;

import com.propane.libmanv1.identity.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
