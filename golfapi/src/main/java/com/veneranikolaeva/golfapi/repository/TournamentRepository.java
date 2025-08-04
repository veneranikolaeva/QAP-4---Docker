package com.veneranikolaeva.golfapi.repository;

import com.veneranikolaeva.golfapi.model.Tournament;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    List<Tournament> findByLocation(String location);
    List<Tournament> findByStartDate(LocalDate startDate);
}