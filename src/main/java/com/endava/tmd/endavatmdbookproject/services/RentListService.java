package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.RentList;
import com.endava.tmd.endavatmdbookproject.repositories.RentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RentListService {
    @Autowired
    private RentListRepository rentListRepository;
    @Autowired
    private BookListService bookListService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    //get all rented books from rent list
    public List<RentList> list() {
        return rentListRepository.findAll();
    }

    //rent a book
    public RentList rent(Long userid, String title, String author, Integer period) {
        if (userService.getUserByUserid(userid) == null) {
            return null;
        }

        BookList bookList = bookListService.getIfAvailable(title, author);

        if (bookList == null) {
            return null;
        }

        RentList newRentList = new RentList();
        newRentList.setUser(userService.getUserByUserid(userid));
        newRentList.setBook(bookList.getBookListID().getBook());

        if (period == 1) {
            newRentList.setPeriod(period + " week");
        } else {
            newRentList.setPeriod(period + " weeks");
        }

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        newRentList.setDate_of_rent(date);

        rentListRepository.saveAndFlush(newRentList);
        bookListService.updateRentid(bookList, newRentList);

        return newRentList;
    }


    //extend renting period
    public RentList extendRent(Long rentid, Integer period) {
        RentList rent = rentListRepository.getRentListByID(rentid);

        if (rent == null) {
            return null;
        }

        Integer newPeriod = Integer.parseInt(rent.getPeriod().split(" ")[0]) + period;
        rent.setPeriod(newPeriod + " weeks");

        rentListRepository.saveAndFlush(rent);
        return rent;
    }

    //see when to return rented books
    public List<RentList> returnBook(Long userid) {
        if (userService.getUserByUserid(userid) == null) {
            return null;
        }

        return rentListRepository.getRentListsByUser_ID(userid);
    }

    //Search by title or author to see if the book/books are available for rent or when they are available
    public Object search(Optional<String> title, Optional<String> author) {
        List<Book> books = new ArrayList<>();

        if (title.isPresent()) {
            books.add(bookService.getBookByTitle(title.get()));
        }

        if (author.isPresent()) {
            books = Stream
                    .concat(books.stream(), bookService.getBooksByAuthor(author.get()).stream())
                    .collect(Collectors.toList());
        }

        if (books.get(0) == null) {
            return 1;
        }

        List<RentList> result = new ArrayList<>();

        for (Book b : books) {
            result.add(getRentListByBook_Id(b.getID()));
        }

        return result;
    }

    public RentList getRentListByBook_Id(Long id) {
        return rentListRepository.getRentListByBook_ID(id);
    }

    //verify who borrowed your books
    public List<RentList> verifyRent(Long userId){
        return rentListRepository
                .getBorrowedBooks(userId)
                .stream()
                .map(this::getRentListByRentid)
                .collect(Collectors.toList());
    }

    //used by other services

    public RentList getRentListByRentid(Long rentid){
        return rentListRepository.getRentListByID(rentid);
    }
}
