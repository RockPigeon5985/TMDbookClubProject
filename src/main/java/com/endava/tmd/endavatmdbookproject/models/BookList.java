package com.endava.tmd.endavatmdbookproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book_lists")
public class BookList {
    @EmbeddedId
    private UserBookID bookListID;

    @OneToOne
    @JoinColumn(name = "rent_id")
    private RentList rent;
}

