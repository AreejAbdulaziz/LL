package com.example.capstone3.Controller;

import com.example.capstone3.Model.Member;
import com.example.capstone3.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/get")
    public ResponseEntity getAllMembers(){
        return ResponseEntity.status(200).body(memberService.getAllMembers());
    }
    @PostMapping("/add")
    public ResponseEntity addMember(@RequestBody@Valid Member member){
        memberService.addMember(member);
        return ResponseEntity.status(200).body("Member Added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMember(@PathVariable Integer id,@RequestBody@Valid Member member){
        memberService.updateMember(id, member);
        return ResponseEntity.status(200).body("Member Updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMember(@PathVariable Integer id){
        memberService.deleteMember(id);
        return ResponseEntity.status(200).body("Member Deleted!");
    }
    //1
    @GetMapping("/getB/{member_id}")
    public ResponseEntity findHackathonsAllowedMyAge(@PathVariable Integer member_id){
        return ResponseEntity.status(200).body( memberService.findHackathonsAllowedMyAge(member_id));
    }
    //2
    @GetMapping("/getLeaders")
    public ResponseEntity findLeaders(){
        return ResponseEntity.status(200).body(memberService.findLeaders());
    }
    //3
    @GetMapping("/getParticipation")
    public ResponseEntity findMostParticipation(){
        return ResponseEntity.status(200).body(memberService.findMostParticipation());
    }
    //4
    @GetMapping("/getExperience")
    public ResponseEntity findHighestExperience(){
        return ResponseEntity.status(200).body(memberService.findHighestExperience());
    }
    //5
    @GetMapping("/getWins")
    public ResponseEntity findHighestWins(){
        return ResponseEntity.status(200).body(memberService.findHighestWins());
    }

    ////6
    @GetMapping("/sortedByExperience")
    public ResponseEntity<List<Member>> getMembersSortedByExperience() {
        List<Member> members = memberService.getAllMembersSortedByExperience();
        return ResponseEntity.ok(members);
    }
    //////7
    @GetMapping("/orderedByWinningTimes")
    public List<Member> getMembersOrderedByWinningTimes() {
        return memberService.getMembersOrderedByWinningTimes();
    }
    ///////8
    @GetMapping("/participationTimes")
    public List<Member> getMembersOrderedByParticipationTimes() {
        return memberService.getMembersOrderedByParticipationTimes();
    }
    ////////9
    @GetMapping("/{skill}")
    public List<Member> getMembersBySkill(@PathVariable String skill) {
        return memberService.getMembersBySkill(skill);
    }
}
