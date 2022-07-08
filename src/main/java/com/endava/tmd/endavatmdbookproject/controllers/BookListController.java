package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.services.BookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booklist")
public class BookListController {
    @Autowired
    private BookListService bookListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookList> list(){
        return bookListService.list();
    }

    @RequestMapping(path = "/add",
            params = "userid",
            method = RequestMethod.POST)
    public Object add(@RequestParam Long userid ,@RequestBody Book book){
        Object bookList = bookListService.add(userid, book);
         if(bookList == null){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
         return bookList;
    }

    @RequestMapping(path = "/rent",
            method = RequestMethod.GET)
    public List<BookList> getBookListByRentidIsNull(){
        return bookListService.getBookListByRentidIsNull();
    }
}
