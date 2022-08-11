package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    //get all books
    public List<Book> list(){
        return bookRepository.findAll();
    }

    //Used by other services

    //get book by title and author
    public Book getBookByTitleAndAuthor(String title, String author){
        return bookRepository.getBookByTitleAndAuthor(title, author);
    }

    //get book by title
    public Book getBookByTitle(String title){
        return bookRepository.getBookByTitle(title);
    }

    //get books by author
    public List<Book> getBooksByAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }

    //create book if it doesn't exist
    public Book create(Book book){
        try{
            return bookRepository.saveAndFlush(book);
        }catch (Exception e){
            return null;
        }
    }

    public Book getBookById(Long id){
        return bookRepository.getBookById(id);
    }
}
