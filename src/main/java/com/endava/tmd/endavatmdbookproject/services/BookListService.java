package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.BookList;
import com.endava.tmd.endavatmdbookproject.models.BookListID;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.repositories.BookListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class BookListService implements Serializable {
    @Autowired
    private BookListRepository bookListRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    public List<BookList> list(){
        return bookListRepository.findAll();
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
}
