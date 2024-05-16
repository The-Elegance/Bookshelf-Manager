package com.thelegance.bookshalf.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class searchDto{
    public String author;
    public String bookName;
    public TypeOrder order;
}
