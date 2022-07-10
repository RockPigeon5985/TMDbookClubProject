package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.repositories.RentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

    public RentList rent(Long userid, String title,
                         String author, String period){
        RentList newRentList = new RentList();

        if(userService.get(userid) == null){
            return null;
        }

        List<BookList> availableForRent = bookListService.getBookListByRentidIsNull();

        Optional<BookList> isAvailable = availableForRent
                .stream()
                .filter(t -> (t.getBookListID().getBook().getTitle() +
                          t.getBookListID().getBook().getAuthor()).equals(title + author))
                .limit(1)
                .findAny();

        if(isAvailable.isPresent()){
            newRentList.setUser(userService.get(userid));
            newRentList.setBook(isAvailable.get().getBookListID().getBook());
            newRentList.setPeriod(period);

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

        return rentList.get();
    }
}
