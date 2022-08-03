package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.WaitingList;
import com.endava.tmd.endavatmdbookproject.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/waitinglist")
public class WaitingListController {
    @Autowired
    private WaitingListService waitingListService;

    //get all from waiting list
    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> list(){
        return waitingListService.list();
    }

    //add user to waiting list
    @RequestMapping(path = "/add",
            method = RequestMethod.POST)
    public Object add(@RequestParam("userid") Long userid, @RequestParam("rentid") Long rentid){
        WaitingList result = waitingListService.add(userid, rentid);

        if(result == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return result;
    }
}
