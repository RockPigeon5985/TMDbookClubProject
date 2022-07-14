package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "rent_list")
public class RentList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "rent_id")
    private Long rentid;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Date date_of_rent;

    public RentList() {
    }

    public Long getRentid() {
        return rentid;
    }

    public void setRentid(Long rentid) {
        this.rentid = rentid;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Date getDate_of_rent() {
        return date_of_rent;
    }

    public void setDate_of_rent(Date date_of_rent) {
        this.date_of_rent = date_of_rent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
