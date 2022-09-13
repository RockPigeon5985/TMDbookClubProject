package com.endava.tmd.endavatmdbookproject.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "wish_lists")
public class WishList {
    @EmbeddedId
    private UserBookID wishListID;

    public WishList() {

    }

    public UserBookID getWishListID() {
        return wishListID;
    }

    public void setWishListID(UserBookID wishListID) {
        this.wishListID = wishListID;
    }
}
