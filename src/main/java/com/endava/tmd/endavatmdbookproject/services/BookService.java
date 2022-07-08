package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> list(){
        return bookRepository.findAll();
    }

    public Book get(Long id){
        return bookRepository.findById(id).get();
    }

    public Book create(Book book){
        try{
            return bookRepository.saveAndFlush(book);
        }catch (Exception e){
            return null;
        }
    }
    public Book getBookByTitleAndAuthor(String title, String author){
        return bookRepository.getBookByTitleAndAuthor(title, author);
    }
}
