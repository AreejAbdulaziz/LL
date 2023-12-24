package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20)")
    private String status;

    @ManyToOne
    @JoinColumn(name = "member_id",referencedColumnName = "id")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "request_id",referencedColumnName = "id")
    @JsonIgnore
    private Team team;
}
