package com.example.capstone3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name Must be  not be null")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String name;
    @NotNull(message = "max Must be  not be null")
    @Positive(message = "must be input Positive")
    @Column(columnDefinition = "int not null")
    private Integer max;
    @NotNull(message = "prize Must be  not be null")
    @Column(columnDefinition = "Int not null")
    private Integer prize;
    @NotEmpty(message = "field Must be  not be null")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^[a-zA-Z]+$")
    private String field;
    @NotNull(message = "min age allowed Must be  not be null")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer minAge;
    @NotNull(message = "max age allowed Must be  not be null")
    @Positive
    @Column(columnDefinition = "int not null")
    private Integer maxAge;
    @DateTimeFormat(fallbackPatterns = "YYY-MM-DD")
    @NotNull(message = "startDate Must be  not be null")
//    @Future(message = "hackathon must be start in the future!")
    @Column(columnDefinition = "date not null")
    private LocalDate startDate; //بداية الهاكثون
    @DateTimeFormat(fallbackPatterns = "YYY-MM-DD")
    @NotNull(message = "endDate Must be  not be null")
//    @Future(message = "hackathon must be end in the future!")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;
    @NotNull(message = "companyName Must be  not be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String companyName;
    @NotNull(message = "city Must be  not be null")
    @Pattern(regexp = "^[a-zA-Z]+$")
    @Column(columnDefinition = "varchar(20) not null")
    private String city;
    private Boolean isOnline;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hackathon")
    private Set<Team> teams;
}
