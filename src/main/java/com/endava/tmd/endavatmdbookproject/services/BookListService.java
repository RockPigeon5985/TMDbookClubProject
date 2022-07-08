package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.*;
import com.endava.tmd.endavatmdbookproject.repositories.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookListService {
    @Autowired
    private BookListRepository bookListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    public List<BookList> list(){
        return bookListRepository.findAll();
    }

    public List<BookList> getByUserId(Long userid){
        return bookListRepository
                .findAll()
                .stream()
                .filter(t -> t.getBookListID().getUser().getUser_id() == userid)
                .collect(Collectors.toList());
    }
    public BookList add(Long userid, Book book){
        BookList bookList = new BookList();
        BookListID bookListID = new BookListID();

        User findUser = userService.get(userid);
        if(findUser == null){
            return null;
        }

        bookListID.setUser(findUser);

        Book findBook = bookService.getBookByTitleAndAuthor(book.getTitle(),book.getAuthor());
        if(findBook == null){
            bookService.create(book);
            bookListID.setBook(book);
        }else {
            bookListID.setBook(findBook);
        }

        bookList.setBookListID(bookListID);
        bookList.setRentid(null);
        return bookListRepository.saveAndFlush(bookList);
    }

    public List<BookList> getBookListByRentidIsNull(){
        return bookListRepository.getBookListByRentidIsNull();
    }

    public void updateRentid(BookList bookList, RentList rentid){
        bookList.setRentid(rentid);
        bookListRepository.saveAndFlush(bookList);
    }
    public List<RentList> verifyRent(Long userId){
        List<BookList> bookList = getByUserId(userId);
        List<RentList> result = new ArrayList<RentList>();
        for(BookList b : bookList){
            if(b.getRentid() != null){
                result.add(b.getRentid());
            }
        }
        return result;
    }
}
