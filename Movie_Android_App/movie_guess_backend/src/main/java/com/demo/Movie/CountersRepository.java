package com.example.demo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountersRepository extends JpaRepository<Counter, Integer> {

    @Query("SELECT c FROM Counter c WHERE c.movieCol=?1 AND c.cand=?2")
    List<Counter> findCounterEntryByColAndCan(String movieCol, String cand);

    @Query("SELECT c FROM Counter c WHERE c.gender=?1")
    List<Counter> findByGender(int gender);

    @Transactional
    @Modifying
    @Query("UPDATE Counter SET value = value + 1 WHERE id =?1 AND gender =?2")
    void increaseCounterValue(int id, int gender);
}
