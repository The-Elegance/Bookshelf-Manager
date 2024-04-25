package com.thelegance.bookshalf.service.impl;

import com.thelegance.bookshalf.model.Book;
import com.thelegance.bookshalf.model.Grade;
import com.thelegance.bookshalf.model.User;
import com.thelegance.bookshalf.repository.BookRepository;
import com.thelegance.bookshalf.repository.GradeRepository;
import com.thelegance.bookshalf.repository.UserRepository;
import com.thelegance.bookshalf.service.GradeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GradeServiceImpl implements GradeService{

    final
    GradeRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public GradeServiceImpl(GradeRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Grade> getAllGrades() {
        return repository.findAll();
    }

    @Override
    public List<Grade> gradesForBook(Long bookId) {
        return repository.findByBookId(bookId);
    }

    @Override
    public List<Grade> gradesForUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Grade rateBook(Long bookId, Long userId, Integer rate) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty() || book.isEmpty() )
        {
            throw new EntityNotFoundException();
        }
        var updt = repository.findByBookIdAndUserId(bookId, userId);
        if(updt != null)
        {
            updt.setRating(rate);
            repository.save(updt);
            return updt;
        }

        Grade grade = new Grade();
        grade.setUser(user.get());
        grade.setRating(rate);
        grade.setBook(book.get());
        grade.setId(UUID.randomUUID());
        repository.save(grade);

        return grade;
    }
}
