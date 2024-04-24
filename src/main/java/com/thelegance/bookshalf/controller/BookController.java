package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    static List<Book> books = new ArrayList<Book>();

    static {
        books.add(new Book(0L, "The Beautiful and Damned", "...", "Francis Scott Key Fitzgerald"));
        books.add(new Book(1L, "Metro 2033", "...", "Dmitry Glukhovsky"));
    }

    @GetMapping(value = "/books")
    @ResponseBody
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping(value = "/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }
}
