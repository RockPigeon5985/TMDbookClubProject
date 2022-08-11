package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.UserBookID;
import com.endava.tmd.endavatmdbookproject.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, UserBookID> {
}
