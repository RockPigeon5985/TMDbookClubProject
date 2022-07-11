package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.models.WaitingList;
import com.endava.tmd.endavatmdbookproject.repositories.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {
    @Autowired
    private WaitingListRepository waitingListRepository;

    public List<WaitingList> list(){
        return waitingListRepository.findAll();
    }

    public WaitingList add(Long userid){
        return null;
    }
}
