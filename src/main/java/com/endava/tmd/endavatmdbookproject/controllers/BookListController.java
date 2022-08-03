package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.services.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/booklist")
public class BookListController {
    @Autowired
    private BookListService bookListService;

    //get all book lists of all users
    @RequestMapping(method = RequestMethod.GET)
    public List<BookList> list(){
        return bookListService.list();
    }

    //add a book in a user book list
    @RequestMapping(path = "/add",
            method = RequestMethod.POST)
    public Object add(@RequestParam("userid") Long userid ,@RequestBody Book book){
        Object bookList = bookListService.add(userid, book);

        if(bookList == null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return bookList;
    }

    //get all books available for rent
    @RequestMapping(path = "/rent",
            method = RequestMethod.GET)
    public Object getBooksForRent(){
        List<Book> result = bookListService.getBooksForRent();

        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }
}
