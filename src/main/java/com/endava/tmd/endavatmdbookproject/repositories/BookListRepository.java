package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.UserBookID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookListRepository extends JpaRepository<BookList, UserBookID> {
    List<BookList> getBookListByRentidIsNull();
    @Query(value = "SELECT book_list.* FROM book_list WHERE bookListID.book.title = ?1 AND bookListID.book.author = ?2 AND book_list.rentid IS NULL",
    nativeQuery = true)
    BookList getIfAvailable(String title, String author);

    BookList getBookListByBookListID_Book_TitleAndBookListID_Book_AuthorAndRentidIsNull(String title, String author);
}
