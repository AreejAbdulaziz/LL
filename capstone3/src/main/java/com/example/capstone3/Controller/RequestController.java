package com.example.capstone3.Controller;

import com.example.capstone3.DTO.RequestDTO;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    @GetMapping("/get")
    public ResponseEntity getAllRequests(){
        return ResponseEntity.status(200).body(requestService.getAllRequests());
    }
    //////////ممكن بعدين اقترح له تيم ثاني
    @PostMapping("/add")
    public ResponseEntity addRequest(@RequestBody@Valid RequestDTO requestDTO){
        requestService.addRequest(requestDTO);
        return ResponseEntity.status(200).body("Request Added");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRequest(@PathVariable Integer id){
        requestService.deleteRequest(id);
        return ResponseEntity.status(200).body("Request Deleted");
    }
    //1
    @GetMapping("/get/requests/{leader_id}")
    public ResponseEntity viewTeamRequestsForOneTeam(@PathVariable Integer leader_id){
        return ResponseEntity.status(200).body(requestService.viewTeamRequestsForOneTeam(leader_id));
    }

    //2
    @PutMapping("/accept/{leader_id}/{request_id}")
    public ResponseEntity acceptRequest(@PathVariable Integer leader_id,@PathVariable Integer request_id){
        requestService.acceptRequest(leader_id, request_id);
        return ResponseEntity.status(200).body("Request Accepted!");
    }
    //3
    @PutMapping("/reject/{leader_id}/{request_id}")
    public ResponseEntity rejectRequest(@PathVariable Integer leader_id,@PathVariable Integer request_id){
        requestService.rejectRequest(leader_id, request_id);
        return ResponseEntity.status(200).body("Request Rejected!");
    }
    //4
    @PostMapping("/addToMem/{leader_id}")
    public ResponseEntity addRequestToMember(@RequestBody@Valid RequestDTO requestDTO,@PathVariable Integer leader_id){
        requestService.addRequestToMember(requestDTO,leader_id);
        return ResponseEntity.status(200).body("Request Added");
    }
    //5
    @GetMapping("/getRequestsM/{member_id}")
    public ResponseEntity viewRequestsForOneMember(@PathVariable Integer member_id){
        return ResponseEntity.status(200).body(requestService.viewRequestsForOneMember(member_id));
    }
    //6
    @PutMapping("/acceptT/{member_id}/{request_id}")
    public ResponseEntity memberAcceptRequest(@PathVariable Integer member_id,@PathVariable Integer request_id){
        requestService.memberAcceptRequest(member_id, request_id);
        return ResponseEntity.status(200).body("Request Accepted");
    }
    //7
    @PutMapping("/rejectT/{member_id}/{request_id}")
    public ResponseEntity memberRejectRequest(@PathVariable Integer member_id,@PathVariable Integer request_id){
        requestService.memberRejectRequest(member_id, request_id);
        return ResponseEntity.status(200).body("Request Rejected");
    }
}
