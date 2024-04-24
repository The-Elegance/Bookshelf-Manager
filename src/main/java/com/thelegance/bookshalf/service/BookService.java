package com.thelegance.bookshalf.service;

import com.thelegance.bookshalf.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Book getById(Long id);
    void add(Book book);
}
