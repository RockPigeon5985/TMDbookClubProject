package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.RentList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface RentListRepository extends JpaRepository<RentList, Long> {
    RentList getRentListByBook_Id(Long id);

    RentList getRentListByRentid(Long id);

    @Query(value = "SELECT bl.rent_id " +
            "FROM book_list AS bl " +
            "WHERE bl.user_id = ?1 AND bl.rent_id IS NOT NULL",
            nativeQuery = true)
    List<Long> getBorrowedBooks(Long userid);

    List<RentList> getRentListsByUser_Userid(Long userid);
}
