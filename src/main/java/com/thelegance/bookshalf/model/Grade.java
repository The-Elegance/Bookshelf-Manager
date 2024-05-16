package com.thelegance.bookshalf.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "grade", schema = "public")

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Min(1)
    @Max(10)
    public Integer rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user.id")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book.id")
    private Book book;

}
