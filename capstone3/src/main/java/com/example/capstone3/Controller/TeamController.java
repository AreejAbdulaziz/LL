package com.example.capstone3.Controller;

import com.example.capstone3.DTO.TeamDTO;
import com.example.capstone3.Model.Member;
import com.example.capstone3.Model.Team;
import com.example.capstone3.Service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    @GetMapping("/get")
    public ResponseEntity getAllTeams(){
        return ResponseEntity.status(200).body(teamService.getAllTeams());
    }

    @PostMapping("/add/{hackathonId}/{memberId}")
    public ResponseEntity addTeam(@RequestBody@Valid TeamDTO teamDTO,@PathVariable Integer hackathonId,@PathVariable Integer memberId){
      teamService.addTeam(teamDTO,hackathonId,memberId);
      return ResponseEntity.status(200).body("Team Added");
    }
    @PutMapping("/update/{team_id}/{member_id}")
    public ResponseEntity updateTeam(@RequestBody@Valid TeamDTO teamDTO,@PathVariable Integer team_id,@PathVariable Integer member_id){
        teamService.updateTeam(teamDTO, team_id, member_id);
        return ResponseEntity.status(200).body("Team Updated");
    }
    @DeleteMapping("/delete/{member_id}")
    public ResponseEntity deleteTeam(@PathVariable Integer member_id){
        teamService.deleteTeam(member_id);
        return ResponseEntity.status(200).body("Team Deleted");
    }
    @PutMapping("/changeLeader/{oldLeader_id}/{newLeader_id}")
    public ResponseEntity changeLeader(@PathVariable Integer oldLeader_id, @PathVariable Integer newLeader_id){
        teamService.changeLeader(oldLeader_id, newLeader_id);
        return ResponseEntity.status(200).body("Leader changed successfully");
    }
}
