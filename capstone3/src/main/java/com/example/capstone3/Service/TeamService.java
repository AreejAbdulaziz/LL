package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.TeamDTO;
import com.example.capstone3.Model.Hackathon;
import com.example.capstone3.Model.Member;
import com.example.capstone3.Model.Team;
import com.example.capstone3.Repository.HackathonRepository;
import com.example.capstone3.Repository.MemberRepository;
import com.example.capstone3.Repository.ProjectRepository;
import com.example.capstone3.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
//    private final MemberService memberService;
//    private final ProjectRepository projectRepository;
    private final HackathonRepository hackathonRepository;
    private final HackathonService hackathonService;
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void addTeam(TeamDTO teamDTO,Integer hackathonId,Integer memberId) {
        Member leader = memberRepository.findMemberById(memberId);
        if(leader==null){
            throw new ApiException("leader not found");
        }
        Hackathon hackathon=hackathonRepository.findHackathonById(hackathonId);
        if(hackathon==null){
            throw new ApiException("The Wanted Hackathon IS Not Found");
        }
        if (!leader.getRole().equals("Leader")) {
            throw new ApiException("member must be a leader to creat team");
        }
        if (leader.getTeam() != null) {
            throw new ApiException("You Are Already In Team!");
        }
        if(leader.getAge()<hackathon.getMinAge()||leader.getAge()>hackathon.getMaxAge()){
            throw new ApiException("You cant register in this hackathon because your age");
        }
        if ( hackathon.getStartDate().isAfter(LocalDate.now())) {

            Team team=new Team(null,teamDTO.getName(),teamDTO.getDescription(),hackathon.getMax(),null,null,hackathon,null);
            teamRepository.save(team);
            hackathonService.assignTeamToHackathon(hackathon.getId(), team.getId());
            assignMemberToTeam(team.getId(), memberId);
        }
        else throw new ApiException("Hackathon's Registration Period Ended!");
    }
    public void updateTeam(TeamDTO teamDTO,Integer team_id,Integer member_id) {
        Member leader = memberRepository.findMemberById(member_id);
        Team oldTeam = teamRepository.findTeamById(team_id);
        if (oldTeam == null || leader == null) {
            throw new ApiException("team or leader not found");
        }
        if (!leader.getRole().equals("Leader")) {
            throw new ApiException("member must be a leader to update team");
        }
        for (Member m : oldTeam.getMembers()) {
            if (m.getId() == member_id) {
                oldTeam.setName(teamDTO.getName());
                oldTeam.setDescription(teamDTO.getDescription());
                teamRepository.save(oldTeam);
            }

        }
    }
    public void deleteTeam(Integer member_id){
        Member leader = memberRepository.findMemberById(member_id);
        if(leader==null){
            throw new ApiException("leader not found");
        }
        if(leader.getTeam()==null){
            throw new ApiException("Team Not Found");
        }
        Team team=teamRepository.findTeamById(leader.getTeam().getId());
        if (!leader.getRole().equals("Leader")) {
            throw new ApiException("member must be a leader to delete team");
        }
        //check leader is leader for this team
        for(Member m: team.getMembers()){
            if(m.getId()==member_id){
                for(Member s: team.getMembers()){
                    s.setTeam(null);
                    memberRepository.save(s);
                }
                team.setMembers(null);
                teamRepository.save(team);
                teamRepository.delete(team);
                break;
            }
        }
    }

    public void assignMemberToTeam(Integer team_id,Integer member_id){
        Team team=teamRepository.findTeamById(team_id);
        Member member=memberRepository.findMemberById(member_id);

        if(team==null||member==null){
            throw new ApiException("team or member not found");
        }
        team.setMaxCap(team.getMaxCap()-1);
        member.setParticipationTimes(member.getParticipationTimes()+1);
        member.setTeam(team);

        memberRepository.save(member);
    }
    //////////
    public void changeLeader(Integer oldLeader_id,Integer newLeader_id){
        Member oldLeader=memberRepository.findMemberById(oldLeader_id);
        Member newLeader=memberRepository.findMemberById(newLeader_id);
        if( oldLeader==null || newLeader==null){
            throw new ApiException("team or old leader or new leader not found");
        }
        if (oldLeader.getTeam()==null||newLeader.getTeam()==null){
            throw new ApiException("old leader or new leader not in the team");
        }
        if(!oldLeader.getRole().equals("Leader")){
            throw new ApiException("you are not leader");
        }
        if(oldLeader.getTeam().getId()!=newLeader.getTeam().getId()){
            throw new ApiException("old leader and new leader not in the same team!");
        }
        oldLeader.setRole("Member");
        newLeader.setRole("Leader");
        memberRepository.save(oldLeader);
        memberRepository.save(newLeader);
    }
}
