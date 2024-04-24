package com.thelegance.bookshalf.repository;

import com.thelegance.bookshalf.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
