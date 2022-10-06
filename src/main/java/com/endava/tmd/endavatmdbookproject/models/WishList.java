package com.endava.tmd.endavatmdbookproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "wish_lists")
public class WishList {
    @EmbeddedId
    private UserBookID wishListID;
}
