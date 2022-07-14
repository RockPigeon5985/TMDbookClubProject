package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "waiting_list")
public class WaitingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long waiting_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "rent_id")
    private RentList rent_id;

    @Column(nullable = false)
    private Date date_of_wait;

    public WaitingList() {
    }

    public Long getWaiting_id() {
        return waiting_id;
    }

    public void setWaiting_id(Long waiting_id) {
        this.waiting_id = waiting_id;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public RentList getRent_id() {
        return rent_id;
    }

    public void setRent_id(RentList rent_id) {
        this.rent_id = rent_id;
    }

    public Date getDate_of_wait() {
        return date_of_wait;
    }

    public void setDate_of_wait(Date date_of_wait) {
        this.date_of_wait = date_of_wait;
    }
}
