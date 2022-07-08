package com.endava.tmd.endavatmdbookproject.services;

import com.endava.tmd.endavatmdbookproject.repositories.RentListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentListService {
    @Autowired
    private RentListRepository rentListRepository;
}
