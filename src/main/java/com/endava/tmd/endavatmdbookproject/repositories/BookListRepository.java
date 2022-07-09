package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.BookListID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BookListRepository extends JpaRepository<BookList, BookListID> {
    public List<BookList> getBookListByRentidIsNull();
}
