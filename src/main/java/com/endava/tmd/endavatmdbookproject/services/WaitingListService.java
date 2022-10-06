package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
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
    private BookService bookService;

    //get all from waiting list
    public List<WaitingList> list(){
        return waitingListRepository.findAll();
    }

    //add user to waiting list
    public WaitingList add(Long userid, Long bookId){
        User user = userService.getUserByUserid(userid);
        if(user == null){
            return null;
        }

        Book book = bookService.getBookById(bookId);
        if(book == null){
            return null;
        }

        WaitingList result = new WaitingList();

        result.setUser(user);
        result.setBook(book);

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        result.setDate_of_wait(date);

        return waitingListRepository.saveAndFlush(result);
    }
}
