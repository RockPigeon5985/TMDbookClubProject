package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.services.RentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rentlist")
public class RentListController {
    @Autowired
    private RentListService rentListService;

    //get all rented books from rent list
    @RequestMapping(method = RequestMethod.GET)
    public List<RentList> list(){
        return rentListService.list();
    }

    //rent a book
    @RequestMapping(path = "/rent",
            method = RequestMethod.POST)
    public Object rent(@RequestParam("userid") Long userid, @RequestParam("title") String title,
                       @RequestParam("author") String author, @RequestParam("period") Integer period){
        Object result = rentListService.rent(userid, title, author, period);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return result;
    }

    //verify who borrowed your books and when will be returned
    @RequestMapping(path = "/verifyRent",
            method = RequestMethod.GET)
    public Object verifyRent(@RequestParam("id") Long id){
        List<RentList> result = rentListService.verifyRent(id);

        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }

    //extend renting period
    @RequestMapping(path = "/extend",
            method = RequestMethod.POST)
    public Object extendRent(@RequestParam("rentid") Long rentid, @RequestParam("period") Integer period){
        RentList result = rentListService.extendRent(rentid, period);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }

    //see when to return rented books
    @RequestMapping(path = "/return",
    method = RequestMethod.GET)
    public Object returnBook(@RequestParam("userid") Long userid){
        List<RentList> result = rentListService.returnBook(userid);

        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }

    //Search by title or author to see if the book/books are available for rent or when they are available
    @RequestMapping(path = "/search",
            method = RequestMethod.GET)
    public Object search(@RequestParam("title") Optional<String> title,
                         @RequestParam("author") Optional<String> author){
        Object result = rentListService.search(title,author);

        if(result.equals(1)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return result;
    }
}
