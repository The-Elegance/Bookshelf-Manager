package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService=bookService;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Book> getAllBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) String bookName,
                                  @RequestParam(required = false) TypeOrder order

    ) {

        return bookService.getAllBooks(new searchDto(author,bookName, order));
    }

    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookDto bookDto) {
        Book book  = new Book(bookDto.name, bookDto.description, bookDto.author);
        bookService.add(book);
    }

    @PutMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.update(id, book);
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    //должно быть просто /books/{id}/read, пользователя бек сам должен определиить(по наличию авторизации),
    @PostMapping("/books/{bookId}/readBy/{userId}")
    public List<Book> readBookByUserId(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.readBookBy(bookId, userId);
    }

    @DeleteMapping("/books/{bookId}/readBy/{userId}")
    public List<Book> DeleteBookByUserId(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.readBookBy(bookId, userId);
    }

    //должно быть просто /books/{id}/addBookToishlist, пользователя бек сам должен определиить(по наличию авторизации),
    @PostMapping("/books/{bookId}/addBookToWishlistBy/{userId}")
    public List<Book> addBookToishlist(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.addBookToWishlist(bookId, userId);
    }

    @DeleteMapping("/books/{bookId}/addBookToWishlistBy/{userId}")
    public List<Book> deleteBookToishlist(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.addBookToWishlist(bookId, userId);
    }
}

