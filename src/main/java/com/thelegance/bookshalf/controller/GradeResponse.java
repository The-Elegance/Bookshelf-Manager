package com.thelegance.bookshalf.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeResponse {
    public BookDto book;
    public UserDto user;
    public Integer rating;
}
