package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "htpp://localhost:3000")
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    //get all books
    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list(){
        return bookService.list();
    }

    @RequestMapping(path = "/suggest", method = RequestMethod.GET)
    public List<Book> suggestBook(@RequestParam("s") String s){
        return bookService.suggestBook(s);
    }
}
