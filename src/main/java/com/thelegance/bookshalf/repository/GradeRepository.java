package com.thelegance.bookshalf.repository;

import com.thelegance.bookshalf.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByUserId(Long userId);
    List<Grade> findByBookId(Long bookId);
    Grade findByBookIdAndUserId(Long bookId, Long userId);
}
