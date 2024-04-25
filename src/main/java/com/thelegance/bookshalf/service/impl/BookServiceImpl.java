package com.thelegance.bookshalf.service.impl;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.repository.BookRepository;
import com.thelegance.bookshalf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        var b  = repository.findAll();

        return b ;
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

