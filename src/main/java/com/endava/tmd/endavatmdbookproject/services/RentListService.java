package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.repositories.RentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RentListService {
    @Autowired
    private RentListRepository rentListRepository;
    @Autowired
    private BookListService bookListService;
    @Autowired
    private UserService userService;
    public List<RentList> list(){
        return rentListRepository.findAll();
    }

    public Optional<RentList> getRentByUserIDAndBookTitle(Long id, String title){
        Predicate<RentList> getByUserID = t -> t.getUser().getUser_id() == id;
        Predicate<RentList> getByBookTitle = t -> t.getBook().getTitle().equals(title);

        return rentListRepository
                .findAll()
                .stream()
                .filter(getByUserID.and(getByBookTitle))
                .findAny();
    }
    public List<RentList> getByUserID(Long userid){
        return rentListRepository
                .findAll()
                .stream()
                .filter(t -> t.getUser().getUser_id() == userid)
                .collect(Collectors.toList());
    }
    public RentList rent(Long userid, String title,
                         String author, Integer period){
        RentList newRentList = new RentList();

        if(userService.get(userid) == null){
            return null;
        }

        List<BookList> availableForRent = bookListService.getAvailableForRent();

        Optional<BookList> isAvailable = availableForRent
                .stream()
                .filter(t -> (t.getBookListID().getBook().getTitle() +
                          t.getBookListID().getBook().getAuthor()).equals(title + author))
                .limit(1)
                .findAny();

        if(isAvailable.isPresent()){
            newRentList.setUser(userService.get(userid));
            newRentList.setBook(isAvailable.get().getBookListID().getBook());

            if(period == 1) {
                newRentList.setPeriod(period + " week");
            }else{
                newRentList.setPeriod(period + " weeks");
            }

            long millis = System.currentTimeMillis();
            Date date = new Date(millis);
            newRentList.setDate_of_rent(date);

            rentListRepository.saveAndFlush(newRentList);
            bookListService.updateRentid(isAvailable.get(), newRentList);

            return newRentList;
        }
        return null;
    }

    public RentList extendRent(Long userid, Integer period, String title){
        if(userService.get(userid) == null){
            return null;
        }

        Optional<RentList> rentList = getRentByUserIDAndBookTitle(userid, title);

        if(rentList.isEmpty()){
            return null;
        }

        Integer newPeriod = Integer.parseInt(rentList.get().getPeriod().split(" ")[0]) + period;
        rentList.get().setPeriod(newPeriod + " weeks");

        rentListRepository.saveAndFlush(rentList.get());
        return rentList.get();
    }

    public List<String> returnBook(Long userid){
        if(userService.get(userid) == null){
            return null;
        }

        List<String> result = new ArrayList<>();
        List<RentList> rentList = getByUserID(userid);

        for(RentList r : rentList){
            result.add(r.getBook().getTitle() + ", " + r.getBook().getAuthor() + ", " + r.getDate_of_rent() + ", " + r.getPeriod());
        }

        return result;
    }
}
