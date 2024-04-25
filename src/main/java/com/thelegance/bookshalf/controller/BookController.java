package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    final
    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    @ResponseBody
    public List<Book> getAllBooks()
    {
        return bookService.getAllBooks();
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable Long id)
    {
        return bookService.getById(id);
    }
}

