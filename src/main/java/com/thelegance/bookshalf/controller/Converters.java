package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.Grade;
import dto.BookDto;
import dto.GradeResponse;
import dto.UserDto;

public class Converters{
    public static GradeResponse converter(Grade grade){
        var grBook = grade.getBook();
        var grUser = grade.getUser();
        var rating = grade.getRating();

        var book = new BookDto(grBook.getName(), grBook.getDescription(), grBook.getAuthor());
        var user = new UserDto(grUser.getUsername(), grUser.getFirstName(), grUser.getLastName());
        return new GradeResponse(book, user, rating);
    }
}
