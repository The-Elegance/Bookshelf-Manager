package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Grade;
import com.thelegance.bookshalf.service.GradeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    final
    GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @GetMapping(value = "/grades")
    @ResponseBody
    public List<Grade> getAllGrades()
    {
        return gradeService.getAllGrades();
    }

    @GetMapping(value = "/grades/by-user/{userId}")
    public List<Grade> getGradesByUserId(@PathVariable Long userId)
    {
        return gradeService.gradesForUser(userId);
    }

    @GetMapping(value = "/grades/by-book/{bookId}")
    public List<Grade> getGradesByBookId(@PathVariable Long bookId)
    {
        return gradeService.gradesForBook(bookId);
    }

    @PutMapping(value = "/grades")
    public Grade rateBook (@RequestBody GradeDto gradeDto ){
        return gradeService.rateBook(gradeDto.bookId, gradeDto.userId, gradeDto.rating);
    }


}

