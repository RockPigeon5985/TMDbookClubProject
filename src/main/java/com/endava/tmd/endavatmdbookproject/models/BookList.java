package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "book_list")
public class BookList {
    @Id
    private BookListID bookListID;

    @Column(name = "rent_id")
    private Long rentid;
    public BookList() {
    }

    public BookListID getBookListID() {
        return bookListID;
    }

    public void setBookListID(BookListID bookListID) {
        this.bookListID = bookListID;
    }

    public Long getRentid() {
        return rentid;
    }

    public void setRentid(Long rentid) {
        this.rentid = rentid;
    }
}

