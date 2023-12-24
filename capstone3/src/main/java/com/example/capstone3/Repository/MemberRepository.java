package com.example.capstone3.Repository;

import com.example.capstone3.Model.Hackathon;
import com.example.capstone3.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberById(Integer id);

    Member findMemberByEmail(String email);

    @Query("select s from Member s where s.role='Leader'")
    List<Member> findLeaders();

    @Query("select m from Member m order by  m.experience DESC")
    List<Member> findAllOrderByExperienceDesc();

    @Query("select m from Member m order by  m.winningTimes DESC")
    List<Member> findAllOrderByWinningTimesDesc();

    @Query("select m from Member m order by   m.participationTimes DESC")
    List<Member> findAllOrderByParticipationTimesDesc();

    @Query("SELECT DISTINCT m FROM Member m JOIN m.skills s WHERE LOWER(s) LIKE LOWER(CONCAT('%', :skill, '%'))")

    List<Member> findBySkillsContainingIgnore(String skill);
}
