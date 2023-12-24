package com.example.capstone3.Repository;

import com.example.capstone3.Model.Member;
import com.example.capstone3.Model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);

    @Query("select r from Request r where r.member.id=?1 and r.team.id=?2")
    Request findRequestByMemberAndTeamId(Integer member_id,Integer team_id);


    @Query("select r from Request r where r.member.id=?1 and r.id=?2")
    Request findRequestByMemberIdAndRequestId(Integer member_id,Integer request_id);
}
