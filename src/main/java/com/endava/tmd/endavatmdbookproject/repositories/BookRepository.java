package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    Book getBookByTitleAndAuthor(String title, String author);
    Book getBookByTitle(String title);
    List<Book> getBooksByAuthor(String author);
}
