package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.WishList;
import com.endava.tmd.endavatmdbookproject.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "htpp://localhost:3000")
@RestController
@RequestMapping("/wishlist")
public class WishListController {
    @Autowired
    private WishListService wishListService;

    //show all from wish list
    @RequestMapping(method = RequestMethod.GET)
    public List<WishList> list(){
        return wishListService.list();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Object add(@RequestParam("userid") Long userid, @RequestParam("bookid") Long bookid){
        Object result = wishListService.add(userid, bookid);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return result;
    }
}
