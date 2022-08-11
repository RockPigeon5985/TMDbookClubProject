package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.*;

@Entity(name = "book_list")
public class BookList {
    @EmbeddedId
    private UserBookID bookListID;
    @OneToOne
    @JoinColumn(name = "rent_id")
    private RentList rentid;

    public BookList() {
    }

    public UserBookID getBookListID() {
        return bookListID;
    }

    public void setBookListID(UserBookID bookListID) {
        this.bookListID = bookListID;
    }

    public RentList getRentid() {
        return rentid;
    }

    public void setRentid(RentList rentid) {
        this.rentid = rentid;
    }
}

