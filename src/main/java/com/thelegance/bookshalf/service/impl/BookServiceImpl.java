package com.thelegance.bookshalf.service.impl;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.repository.BookRepository;
import com.thelegance.bookshalf.service.BookService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) repository.findAll();
    }

    @Override
    public Book getById(Long id) {
        var book = repository.findById(id);
        return book.orElse(null);
    }

    @Override
    public void add(Book book) {
        repository.save(book);
    }
}
