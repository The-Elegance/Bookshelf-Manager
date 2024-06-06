package com.thelegance.bookshalf.service.impl;

import dto.TypeOrder;
import dto.SearchDto;
import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.model.User;
import com.thelegance.bookshalf.repository.BookRepository;
import com.thelegance.bookshalf.repository.UserRepository;
import com.thelegance.bookshalf.service.BookService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    final
    BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Book> getAllBooks(SearchDto searchDto) {
        var enumerable = bookRepository.findAll().stream();

        if(searchDto.getBookName() != null && !searchDto.getBookName().isEmpty())
            enumerable = enumerable.filter(book -> book.getName().contains(searchDto.getBookName()));

        if(searchDto.getAuthor() != null && !searchDto.getAuthor().isEmpty())
            enumerable = enumerable.filter(book -> book.getAuthor().contains(searchDto.getAuthor()));

        if(searchDto.getOrder() != null && searchDto.getOrder() != TypeOrder.None)
            enumerable = enumerable.sorted(searchDto.getOrder() == TypeOrder.Asc?
                    Comparator.comparing(Book::getName)
                    : Comparator.comparing(Book::getName).reversed());

        return enumerable.toList();

    }

    @Override
    public Book getById(Long id) {
        var book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void update(Long id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> readBookBy(Long bookId, Long userId){
        var bookAndUser = getBookAndUser(bookId, userId);
        var book = bookAndUser.a;
        var user = bookAndUser.b;

        var books = user.getReadBooks();
        books.add(book);
        user.setReadBooks(books);
        userRepository.save(user);
        return  books.stream().toList();
    }

    @Override
    public List<Book> DeleteBookFromReadBy(Long bookId, Long userId) {
        var bookAndUser = getBookAndUser(bookId, userId);
        var book = bookAndUser.a;
        var user = bookAndUser.b;

        var books = user.getReadBooks();
        user.setReadBooks(books.stream().filter(b -> !Objects.equals(b.getId(), book.getId())).collect(Collectors.toSet()));
        userRepository.save(user);

        return user.getReadBooks().stream().toList();
    }

    @Override
    public List<Book> addBookToWishlist(Long bookId, Long userId){

        var bookAndUser = getBookAndUser(bookId, userId);
        var book = bookAndUser.a;
        var user = bookAndUser.b;


        var books = user.getDesiredBooks();
        books.add(book);
        user.setDesiredBooks(books);
        userRepository.save(user);
        return  books.stream().toList();
    }

    @Override
    public List<Book> deleteBookFromWishlist(Long bookId, Long userId) {
        var bookAndUser = getBookAndUser(bookId, userId);
        var book = bookAndUser.a;
        var user = bookAndUser.b;

        var books = user.getDesiredBooks();
        user.setDesiredBooks(books.stream().filter(b -> !Objects.equals(b.getId(), book.getId())).collect(Collectors.toSet()));
        userRepository.save(user);

        return user.getDesiredBooks().stream().toList();
    }

    private Pair<Book, User> getBookAndUser(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        if (book == null || user == null)
        {
            throw new IllegalArgumentException("таких данных нет");
        }
        return new Pair<>(book, user);
    }
}

