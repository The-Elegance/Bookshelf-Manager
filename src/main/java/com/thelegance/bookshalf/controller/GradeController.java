package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Grade;
import com.thelegance.bookshalf.service.GradeService;
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
        return gradeService.getAllGrades().stream().map(this::converter).toList();
    }

    @GetMapping(value = "/grades/by-user/{userId}")
    public List<GradeResponse> getGradesByUserId(@PathVariable Long userId)
    {
        return gradeService.gradesForUser(userId).stream().map(this::converter).toList();
    }

    @GetMapping(value = "/grades/by-book/{bookId}")
    public List<GradeResponse> getGradesByBookId(@PathVariable Long bookId)
    {
        return gradeService.gradesForBook(bookId).stream().map(this::converter).toList();
    }

    @PutMapping(value = "/grades")
    public GradeResponse rateBook (@RequestBody GradeDto gradeDto ){
        return converter(gradeService.rateBook(gradeDto.bookId, gradeDto.userId, gradeDto.rating));
    }

    public GradeResponse converter(Grade grade){
        var grBook = grade.getBook();
        var grUser = grade.getUser();
        var rating = grade.getRating();

        var book = new BookDto(grBook.getName(), grBook.getDescription(), grBook.getAuthor());
        var user = new UserDto(grUser.getUsername(), grUser.getFirstName(), grUser.getLastName());
        return new GradeResponse(book, user, rating);
    }

}

