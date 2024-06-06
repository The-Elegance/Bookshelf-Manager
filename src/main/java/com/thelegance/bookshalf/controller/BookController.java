package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.model.User;
import com.thelegance.bookshalf.service.BookService;
import com.thelegance.bookshalf.service.impl.JwtService;
import com.thelegance.bookshalf.service.impl.UserServiceImpl;
import dto.BookDto;
import dto.BookResponse;
import dto.TypeOrder;
import dto.SearchDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)

@SecurityRequirement(name = "JWT")
public class BookController {
    final BookService bookService;
    final JwtService jwtService;
    final UserServiceImpl userService;

    public BookController(BookService bookService, UserServiceImpl userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.bookService=bookService;
    }

    @GetMapping("/books")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<BookResponse> getAllBooks(@RequestParam(required = false) String author,
                                  @RequestParam(required = false) String bookName,
                                  @RequestParam(required = false) TypeOrder order

    ) {
        return bookService.getAllBooks(new SearchDto(author,bookName, order)).stream().map(this::ConvertToBookResponse).toList();
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
    @PostMapping("/books/{bookId}/readBy/me")
    public List<BookResponse> readBookByUserId(@PathVariable Long bookId, @RequestHeader("Authorization") String token) {
        var userId = GetUserForToken(token.split(" ")[1]);
        return bookService.readBookBy(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    @DeleteMapping("/books/{bookId}/deleteFromReadBy/me")
    public List<BookResponse> DeleteBookByUserId(@RequestHeader("Authorization") String token,@PathVariable Long bookId) {

        var userId = GetUserForToken(token.split(" ")[1]);
        return bookService.DeleteBookFromReadBy(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    //должно быть просто /books/{id}/addBookToishlist, пользователя бек сам должен определиить(по наличию авторизации),
    @PostMapping("/books/{bookId}/addBookToWishlistBy/me")
    public List<BookResponse> addBookToWishlist(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        var userId = GetUserForToken(token.split(" ")[1]);
        return bookService.addBookToWishlist(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    @DeleteMapping("/books/{bookId}/addBookToWishlistBy/me")
    public List<BookResponse> deleteBookToWishlist(@RequestHeader("Authorization") String token, @PathVariable Long bookId) {
        var userId = GetUserForToken(token.split(" ")[1]);
        return bookService.deleteBookFromWishlist(bookId, userId).stream().map(this::ConvertToBookResponse).toList();
    }

    private BookResponse ConvertToBookResponse(Book book) {
        var grades = book.getGrades().stream().map(Converters::converter).toList();

        return new BookResponse(book.getName(), book.getDescription(), book.getAuthor(), grades);
    }

    private Long GetUserForToken(String Token){
        var username = jwtService.extractUserName(Token);
        return userService.findByUsername(username).getId();
    }
}

