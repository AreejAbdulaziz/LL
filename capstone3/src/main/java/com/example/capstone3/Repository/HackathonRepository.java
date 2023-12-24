package com.example.capstone3.Repository;

import com.example.capstone3.Model.Hackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HackathonRepository extends JpaRepository<Hackathon,Integer> {
    Hackathon findHackathonById(Integer id);


    @Query("select h from Hackathon h where h.startDate < ?1")
    List<Hackathon> findHackathonsStartingBeforeDate(LocalDate targetDate);


    @Query("select h from Hackathon h where h.startDate > ?1")
    List<Hackathon> findHackathonsStartingAfterDate(LocalDate targetDate);

    @Query("select h from Hackathon h where h.startDate between ?1 AND ?2")
    List<Hackathon> findHackathonsByStartDateBetweenAndEndDate(LocalDate startDate, LocalDate endDate);

    @Query("select h from Hackathon h where h.minAge >= 5 AND h.maxAge <= 11")
    List<Hackathon> findHackathonChildren( );


    @Query("select h from Hackathon h where h.minAge >= 12 AND h.maxAge <= 18")
    List<Hackathon> findHackathonsForTeens( );

    @Query("select h from Hackathon h where h.minAge > 18")
    List<Hackathon> findHackathonsForAdults( );

    List<Hackathon> findHackathonByField(String fields);

    List<Hackathon> findHackathonByCompanyName(String CompanyName);

    List<Hackathon> findHackathonsByIsOnlineTrue();

    List<Hackathon> findHackathonsByCity(String city);

    Hackathon findHackathonByName(String name);

    @Query("select h from Hackathon h where h.endDate<?1")
    List<Hackathon> findHackathonByEndDate(LocalDate date);

}
