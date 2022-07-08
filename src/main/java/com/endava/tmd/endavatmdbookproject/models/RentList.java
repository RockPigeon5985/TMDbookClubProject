package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "rent_list")
public class RentList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rent_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book_id;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private Date date_of_rent;

    public RentList() {
    }

    public Long getRent_id() {
        return rent_id;
    }

    public void setRent_id(Long rent_id) {
        this.rent_id = rent_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Book getBook_id() {
        return book_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
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
}
