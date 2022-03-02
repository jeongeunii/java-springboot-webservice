package com.jeuni.example.springboot.domain.samples;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SamplesRepository extends JpaRepository<Samples, Long> {

    @Query("SELECT s FROM Samples s ORDER BY s.type")
    List<Samples> findAllSamples();
}
