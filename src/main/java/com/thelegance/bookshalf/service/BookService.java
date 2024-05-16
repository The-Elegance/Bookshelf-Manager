package com.thelegance.bookshalf.service;

import com.thelegance.bookshalf.controller.searchDto;
import com.thelegance.bookshalf.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks(searchDto dto);
    Book getById(Long id);
    void add(Book book);
    void update(Long id, Book book);
    void delete(Long id);

    List<Book> readBookBy(Long bookId, Long userId);
    List<Book> DeleteBookFromReadBy(Long bookId, Long userId);

    List<Book> addBookToWishlist(Long bookId, Long userId);
    List<Book> deleteBookFromWishlist(Long bookId, Long userId);
}


