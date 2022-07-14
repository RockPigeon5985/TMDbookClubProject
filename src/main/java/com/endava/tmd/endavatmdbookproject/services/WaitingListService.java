package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.models.WaitingList;
import com.endava.tmd.endavatmdbookproject.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class WaitingListService {
    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RentListService rentListService;

    //get all from waiting list
    public List<WaitingList> list(){
        return waitingListRepository.findAll();
    }

    //add user to waiting list
    public WaitingList add(Long userid, Long rentid){
        User user = userService.getUserByUserid(userid);
        if(user == null){
            return null;
        }

        RentList rentList = rentListService.getRentListByRentid(rentid);
        if(rentList == null){
            return null;
        }

        WaitingList result = new WaitingList();

        result.setUser_id(user);
        result.setRent_id(rentList);

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        result.setDate_of_wait(date);

        return waitingListRepository.saveAndFlush(result);
    }
}
