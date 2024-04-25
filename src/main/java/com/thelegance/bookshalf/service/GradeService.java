package com.thelegance.bookshalf.service;

import com.thelegance.bookshalf.model.Grade;

import java.util.List;

public interface GradeService{
    List<Grade> getAllGrades();
    List<Grade> gradesForBook(Long bookId);
    List<Grade> gradesForUser(Long userId);
    Grade rateBook(Long bookId, Long userId, Integer rate);

}
