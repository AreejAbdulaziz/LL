package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Hackathon;
import com.example.capstone3.Model.Member;
import com.example.capstone3.Repository.MemberRepository;
import com.example.capstone3.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final HackathonService hackathonService;
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public void addMember(Member member){
        memberRepository.save(member);
    }
    public void updateMember(Integer id,Member member){
        Member oldMember=memberRepository.findMemberById(id);
        if(oldMember==null){
            throw new ApiException("Member Not Found");
        }
        if(oldMember.getTeam()!=null && !oldMember.getRole().equals(member.getRole())) //لو هو بتيم مايغير الرول حقه
        {
            throw new ApiException("You Cant Change Your Role Where You With Team");
        }
        oldMember.setEmail(member.getEmail());
        oldMember.setName(member.getName());
        oldMember.setAge(member.getAge());
        oldMember.setField(member.getField());
        oldMember.setSkills(member.getSkills());
        oldMember.setExperience(member.getExperience());
        oldMember.setNationality(member.getNationality());
        oldMember.setParticipationTimes(member.getParticipationTimes());
        oldMember.setWinningTimes(member.getWinningTimes());
        oldMember.setHackathonWinNames(member.getHackathonWinNames());
        oldMember.setRole(member.getRole());

        memberRepository.save(oldMember);
    }
    ///////////////////////////////////////الكابستي تزيد واحد
    public void deleteMember(Integer id){
        Member member=memberRepository.findMemberById(id);
        if(member==null){
            throw new ApiException("Member Not Found");
        }
        if(member.getTeam()!=null) {
            member.getTeam().setMaxCap(member.getTeam().getMaxCap()+1);
            if (member.getRole().equals("Leader")) {
                for (Member m : member.getTeam().getMembers()) {
                    if (m.getId() != id) {
                        m.setRole("Leader");
                        member.setTeam(null);
                        memberRepository.delete(member);
                        break;
                    }
                }
            }
        }
        memberRepository.delete(member);
    }
    ////////1//////////////////////////////////////
    public List<Hackathon> findHackathonsAllowedMyAge(Integer member_id){
        Member member=memberRepository.findMemberById(member_id);
        if(member==null){
            throw new ApiException("member not found");
        }
        if(member.getAge()>= 5&&member.getAge() <= 11){
            return hackathonService.findHackathonChildren();
        } else if (member.getAge() >= 12 && member.getAge() <= 18) {
            return hackathonService.findHackathonsForTeens();
        }
        else return hackathonService.findHackathonsForAdults();
    }
    ////////2
    public List<Member> findLeaders(){
        return memberRepository.findLeaders();
    }

    ////////3

    public Member findMostParticipation(){
        List<Member> members=memberRepository.findAll();
        if(members.isEmpty()){
            throw new ApiException("No Members");
        }
        Member member=members.get(0);
        for (int i=0;i<members.size();i++){
            if(members.get(i).getParticipationTimes()>member.getParticipationTimes()){
                member=members.get(i);
            }
        }
        return member;
    }
    ////////4
    public Member findHighestExperience(){
        List<Member> members=memberRepository.findAll();
        if(members.isEmpty()){
            throw new ApiException("No Members");
        }
        Member member=members.get(0);
        for (int i=0;i<members.size();i++){
            if(members.get(i).getExperience()>member.getExperience()){
                member=members.get(i);
            }
        }
        return member;
    }
    ////////5
    public Member findHighestWins(){
        List<Member> members=memberRepository.findAll();
        if(members.isEmpty()){
            throw new ApiException("No Members");
        }
        Member member=members.get(0);
        for (int i=0;i<members.size();i++){
            if(members.get(i).getWinningTimes()>member.getWinningTimes()){
                member=members.get(i);
            }
        }
        return member;
    }
    /////6
    public List<Member> getAllMembersSortedByExperience() {
        return memberRepository.findAllOrderByExperienceDesc();
    }
    //////7
    public List<Member> getMembersOrderedByWinningTimes() {
        return memberRepository.findAllOrderByWinningTimesDesc();
    }
    ///////8
    public List<Member> getMembersOrderedByParticipationTimes() {
        return memberRepository.findAllOrderByParticipationTimesDesc();
    }
    ///////9
    public List<Member> getMembersBySkill(String skill) {
        return memberRepository.findBySkillsContainingIgnore(skill);
    }

}
