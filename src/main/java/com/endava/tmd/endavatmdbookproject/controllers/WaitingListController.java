package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.WaitingList;
import com.endava.tmd.endavatmdbookproject.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waitinglist")
public class WaitingListController {
    @Autowired
    private WaitingListService waitingListService;
    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingList> list(){
        return waitingListService.list();
    }

    @RequestMapping(path = "/wait",
            method = RequestMethod.GET)
    public WaitingList add(@RequestParam("userid") Long userid){
        return waitingListService.add(userid);
    }
}
