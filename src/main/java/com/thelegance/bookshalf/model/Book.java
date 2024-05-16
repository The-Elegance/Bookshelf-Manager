package com.thelegance.bookshalf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books", schema = "public")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    String name;
    String description;
    String author;

    public Book(String name, String description, String author) {
        this.name = name;
        this.description = description;
        this.author = author;
    }


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = false)
    private Set<Grade> grades = new HashSet<>();
}
