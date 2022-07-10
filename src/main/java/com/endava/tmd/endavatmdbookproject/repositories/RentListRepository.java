package com.endava.tmd.endavatmdbookproject.repositories;

import com.endava.tmd.endavatmdbookproject.models.RentList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RentListRepository extends JpaRepository<RentList, Long> {
}
