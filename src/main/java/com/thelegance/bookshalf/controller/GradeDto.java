package com.thelegance.bookshalf.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeDto{
    public Long bookId;
    public Long userId;
    public Integer rating;
}


