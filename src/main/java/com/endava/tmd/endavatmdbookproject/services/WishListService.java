package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.models.User;
import com.endava.tmd.endavatmdbookproject.models.UserBookID;
import com.endava.tmd.endavatmdbookproject.models.WishList;
import com.endava.tmd.endavatmdbookproject.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class WishListService {
    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public List<WishList> list(){
        return wishListRepository.findAll();
    }

    public WishList add(Long userid, Long bookid){
        User user = userService.getUserByUserid(userid);

        if(user == null){
            return null;
        }

        Book book = bookService.getBookById(bookid);

        if(book == null){
            return null;
        }

        UserBookID userBookID = new UserBookID();
        userBookID.setUser(user);
        userBookID.setBook(book);

        WishList result = new WishList();
        result.setWishListID(userBookID);

        try {
            return wishListRepository.saveAndFlush(result);
        }catch (Exception e) {
            return null;
        }
    }

    public List<WishList> show(Long userid){
        return wishListRepository.getWishListsByWishListID_UserID(userid);
    }

    public ResponseEntity<?> delete(Long userid, Long bookid){
        try {
            wishListRepository.delete(wishListRepository.getWishListsByWishListID_UserIDAndWishListID_BookID(userid, bookid));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
