package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.services.RentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentlist")
public class RentListController {
    @Autowired
    private RentListService rentListService;

    @RequestMapping(method = RequestMethod.GET)
    public List<RentList> list(){
        return rentListService.list();
    }

    @RequestMapping(path = "/rent",
    method = RequestMethod.POST)
    public Object rent(@RequestParam("userid") Long userid, @RequestParam("title") String title,
                       @RequestParam("author") String author, @RequestParam("period") String period){
        Object r = rentListService.rent(userid, title, author, period);
        if(r == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return r;
    }

    @RequestMapping(path = "/extend",
    method = RequestMethod.GET)
    public Object extendRent(@RequestParam("userid") Long userid, @RequestParam("period") Integer period,
                             @RequestParam("title") String title){
        RentList rentList = rentListService.extendRent(userid, period, title);

        if(rentList == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return rentList;
    }

    @RequestMapping(path = "/return",
    method = RequestMethod.GET)
    public Object returnBook(@RequestParam("userid") Long userid){
        List<String> result = rentListService.returnBook(userid);

        if(result.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }
}
