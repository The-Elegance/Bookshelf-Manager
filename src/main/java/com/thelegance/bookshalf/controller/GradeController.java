package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Grade;
import com.thelegance.bookshalf.service.GradeService;
import dto.BookDto;
import dto.GradeDto;
import dto.GradeResponse;
import dto.UserDto;
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
    public List<GradeResponse> getAllGrades()
    {

        return gradeService.getAllGrades().stream().map(Converters::converter).toList();
    }

    @GetMapping(value = "/grades/by-user/{userId}")
    public List<GradeResponse> getGradesByUserId(@PathVariable Long userId)
    {
        return gradeService.gradesForUser(userId).stream().map(Converters::converter).toList();
    }

    @GetMapping(value = "/grades/by-book/{bookId}")
    public List<GradeResponse> getGradesByBookId(@PathVariable Long bookId)
    {
        return gradeService.gradesForBook(bookId).stream().map(Converters::converter).toList();
    }

    @PutMapping(value = "/grades")
    public GradeResponse rateBook (@RequestBody GradeDto gradeDto ){
        return Converters.converter(gradeService.rateBook(gradeDto.getBookId(), gradeDto.getUserId(), gradeDto.getRating()));
    }
}

