package com.example.capstone3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Email
    @NotEmpty(message = "email Must be  not be null")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;
    @NotEmpty(message = "name Must be  not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotNull(message = "age Must be  not be null")
    @Min(value = 5,message = "Must be age more than or equal 5")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotEmpty(message = "field Must be  not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @Column(columnDefinition = "varchar(20) not null")
    private String field;
    @ElementCollection
    private List<String> skills;
    @NotNull(message = "experience Must be  not be null")
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer experience;
    @NotEmpty(message = "nationality Must be  not be null")
    @Pattern(regexp = "^[a-zA-Z ]+$")
    @Column(columnDefinition = "varchar(20) not null")
    private String nationality;
    @NotNull(message = "participationTimes Must be  not be null")
    @PositiveOrZero
    private Integer participationTimes;
    @NotNull(message = "winningTimes Must be  not be null")
    @PositiveOrZero
    @Column(columnDefinition = "int not null")
    private Integer winningTimes;
    private String requestStatus;
    @ElementCollection
    private List<String> hackathonWinNames;
    @NotEmpty(message = "role Must not be null")
    @Pattern(regexp = "(Member|Leader)")
    @Column(columnDefinition = "varchar(20) not null")
    private String role;


    @ManyToOne
    @JoinColumn(name = "team_id",referencedColumnName = "id")
    @JsonIgnore
    private Team team;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "member")
    private Set<Request>requests;
}
