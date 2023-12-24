package com.example.capstone3.Repository;

import com.example.capstone3.Model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,Integer> {
    Team findTeamById(Integer id);

}
