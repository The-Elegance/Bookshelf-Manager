package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.service.BookService;
import dto.BookDto;
import dto.BookResponse;
import dto.TypeOrder;
import dto.searchDto;
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
    public List<BookResponse> getAllBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) String bookName,
                                  @RequestParam(required = false) TypeOrder order

    ) {

        return bookService.getAllBooks(new searchDto(author,bookName, order)).stream().map(this::ConvertToBookResponse).toList();
    }

    @PostMapping(value = "/books")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBook(@RequestBody BookDto bookDto) {
        Book book  = new Book(bookDto.getName(), bookDto.getDescription(), bookDto.getAuthor());
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
    public BookResponse getBookById(@PathVariable Long id) {
        return ConvertToBookResponse(bookService.getById(id));
    }

    //должно быть просто /books/{id}/read, пользователя бек сам должен определиить(по наличию авторизации),
    @PostMapping("/books/{bookId}/readBy/{userId}")
    public List<BookResponse> readBookByUserId(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.readBookBy(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    @DeleteMapping("/books/{bookId}/deleteFromReadBy/{userId}")
    public List<BookResponse> DeleteBookByUserId(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.DeleteBookFromReadBy(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    //должно быть просто /books/{id}/addBookToishlist, пользователя бек сам должен определиить(по наличию авторизации),
    @PostMapping("/books/{bookId}/addBookToWishlistBy/{userId}")
    public List<BookResponse> addBookToishlist(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.addBookToWishlist(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    @DeleteMapping("/books/{bookId}/addBookToWishlistBy/{userId}")
    public List<BookResponse> deleteBookToishlist(@PathVariable Long bookId, @PathVariable Long userId) {
        return bookService.deleteBookFromWishlist(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    private BookResponse ConvertToBookResponse(Book book) {
        var grades = book.getGrades().stream().map(Converters::converter).toList();

        return new BookResponse(book.getName(), book.getDescription(), book.getAuthor(), grades);
    }
}

