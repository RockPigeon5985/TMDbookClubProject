package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByTitleAndAuthor(String title, String author);
    Book getBookByTitle(String title);
    Book getBookByID(Long id);
    List<Book> getBooksByAuthor(String author);
    @Query(value = "SELECT * FROM books " +
            "WHERE title LIKE ?1 " +
            "OR author LIKE ?1",
            nativeQuery = true)
    List<Book> suggestBook(String reg);
}
