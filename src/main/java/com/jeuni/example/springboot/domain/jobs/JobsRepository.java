package com.jeuni.example.springboot.domain.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobsRepository extends JpaRepository<Jobs, Long> {

    @Query("SELECT j FROM Jobs j ORDER BY j.date DESC")
    List<Jobs> findAllJobs();

    @Query("SELECT j FROM Jobs j WHERE j.date = :date ORDER BY j.date DESC")
    List<Jobs> findByDate(@Param("date") String date);
}
