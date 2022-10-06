package com.endava.tmd.endavatmdbookproject.models;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long ID;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private Integer year;
}