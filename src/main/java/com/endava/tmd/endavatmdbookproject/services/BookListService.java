package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.*;
import com.endava.tmd.endavatmdbookproject.repositories.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class BookListService {
    @Autowired
    private BookListRepository bookListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    //get all book lists
    public List<BookList> list(){
        return bookListRepository.findAll();
    }

    //add a book in a user book list
    public BookList add(Long userid, Book book){
        BookList bookList = new BookList();
        UserBookID bookListID = new UserBookID();

        User findUser = userService.getUserByUserid(userid);

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

    //get all books available for rent
    public List<Book> getBooksForRent(){
        List<BookList> bookList = bookListRepository.getBookListByRentidIsNull();

        if(bookList.isEmpty()){
            return null;
        }

        List<Book> result = new ArrayList<>();

        for(BookList b : bookList){
            result.add(b.getBookListID().getBook());
        }

        return result;
    }



    //Used for other services

    //get book if is available for rent
    public BookList getIfAvailable(String title, String author){
        return bookListRepository.getIfAvailable(title, author);
    }

    //update rent status
    public void updateRentid(BookList bookList, RentList rentid){
        bookList.setRentid(rentid);
        bookListRepository.saveAndFlush(bookList);
    }

    //get book if available for rent
    public BookList getBookAvailableForRent(String title, String author){
        return bookListRepository.getBookListByBookListID_Book_TitleAndBookListID_Book_AuthorAndRentidIsNull(title, author);
    }
}
