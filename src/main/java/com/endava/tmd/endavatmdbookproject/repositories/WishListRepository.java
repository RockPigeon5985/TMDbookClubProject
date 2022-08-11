package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.UserBookID;
import com.endava.tmd.endavatmdbookproject.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, UserBookID> {

    List<WishList> getWishListsByWishListID_UserUserid(Long id);
}
