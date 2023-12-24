package com.example.capstone3.Controller;

import com.example.capstone3.Model.Hackathon;
import com.example.capstone3.Service.HackathonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hackathon")
@RequiredArgsConstructor
public class HackathonController {
    private final HackathonService hackathonService;
    @GetMapping("/get")
    public ResponseEntity getAllHackathon(){
        return ResponseEntity.status(200).body(hackathonService.getAllHackathon());
    }
    @PostMapping("/add")
    public ResponseEntity addHackathon(@RequestBody@Valid Hackathon hackathon){
        hackathonService.addHackathon(hackathon);
        return ResponseEntity.status(200).body("Hackathon Added!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateHackathon(@PathVariable Integer id,@RequestBody@Valid Hackathon hackathon){
        hackathonService.updateHackathon(id, hackathon);
        return ResponseEntity.status(200).body("Hackathon Updated!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHackathon(@PathVariable Integer id){
       hackathonService.deleteHackathon(id);
       return ResponseEntity.status(200).body("Hackathon Deleted!");
    }

    //--- endpoint 1  اند بوينت تعرض الجائزه الاعلى ---
    @GetMapping("/highestPrize")
    public ResponseEntity getHighestPrize() {
        return ResponseEntity.status(200).body(hackathonService.getHighestPrize());
    }


    //--- endpoint 2  اند بوينت تعرض الهاكثونات تبدا قبل تاريخ معين ---
    @GetMapping("/startingBeforeDate/{targetDate}")
    public ResponseEntity<List<Hackathon>> getHackathonsStartingBeforeDate(@PathVariable LocalDate targetDate) {
        return ResponseEntity.status(200).body(hackathonService.getHackathonsStartingBeforeDate(targetDate));
    }
    //--- endpoint 3  اند بوينت تعرض الهاكثونات تبدا بعد تاريخ معين ---
    @GetMapping("/startingAfterDate/{targetDate}")
    public ResponseEntity<List<Hackathon>> getHackathonsStartingAfterDate(
            @PathVariable LocalDate targetDate) {
        return ResponseEntity.status(200).body(hackathonService.getHackathonsStartingAfterDate(targetDate));
    }
    //--- endpoint 4  اند بوينت تعرض الهاكثونات تبدا بين تاريخين معينه ---
    @GetMapping("/byStartDateBetweenAndEndDate/{firstDate}/{secondDate}")
    public ResponseEntity<List<Hackathon>> findHackathonsByStartDateBetweenAndEndDate(@PathVariable LocalDate firstDate, @PathVariable LocalDate secondDate) {
        List<Hackathon> hackathons = hackathonService.findHackathonsByStartDateBetweenAndEndDate(firstDate, secondDate);
        return ResponseEntity.status(200).body(hackathons);
    }
    //--- endpoint 5 اند بوينت تعرض الهاكثونات للاطفال ---
    @GetMapping("/children")
    public ResponseEntity<List<Hackathon>> findHackathonChildren() {
        return ResponseEntity.status(200).body(hackathonService.findHackathonChildren());
    }

    //--- endpoint 6  اند بوينت تعرض الهاكثونات للمراهقين ---
    @GetMapping("/teens")
    public ResponseEntity<List<Hackathon>> findHackathonsForTeens() {
        return ResponseEntity.status(200).body(hackathonService.findHackathonsForTeens());
    }

    //--- endpoint 7  اند بوينت تعرض الهاكثونات للبالغين  ---
    @GetMapping("/adults")
    public ResponseEntity<List<Hackathon>> findHackathonsForAdults() {
        return ResponseEntity.ok(hackathonService.findHackathonsForAdults());
    }
    //--- endpoint 8  اند بوينت تعرض الهاكثونات لمجال معين  ---
    @GetMapping("/byField/{field}")
    public ResponseEntity<List<Hackathon>> findHackathonByField(@PathVariable String field) {
        List<Hackathon> hackathons = hackathonService.findHackathonByField(field);
        return ResponseEntity.status(200).body(hackathons);
    }
    //--- endpoint 9  اند بوينت تعرض هاكثونات لجهه معينه  ---
    @GetMapping("/byCompanyName/{companyName}")
    public ResponseEntity<List<Hackathon>> findHackathonByCompanyName(@PathVariable String companyName) {
        List<Hackathon> hackathons = hackathonService.findHackathonByCompanyName(companyName);
        return ResponseEntity.status(200).body(hackathons);
    }
    //--- endpoint 10  اند بوينت تعرض هاكثونات اونلاين  ---
    @GetMapping("/online")
    public ResponseEntity<List<Hackathon>> findOnlineHackathons() {
        List<Hackathon> onlineHackathons = hackathonService.findOnlineHackathons();
        return ResponseEntity.status(200).body(onlineHackathons);
    }

    //--- endpoint 11  اند بوينت تعرض هاكثونات مدينه معينه  ---
    @GetMapping("/byCity/{city}")
    public ResponseEntity<List<Hackathon>> findHackathonsByCity(@PathVariable String city) {
        List<Hackathon> hackathons = hackathonService.findHackathonsByCity(city);
        return ResponseEntity.status(200).body(hackathons);
    }
    //--- endpoint 12  اند بوينت ابحث عن الهاكثون باسمه  ---
    @GetMapping("/byName/{name}")
    public ResponseEntity<Hackathon> getHackathonByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(hackathonService.findHackathonByName(name));
    }
    //--- endpoint 13  اند بوينت تفكك التيم عشان يدخل مع تيمز ثانيه و يقدر يسجل بهاكثونات ثانيه  ---
    @PutMapping("/endHackathon")
    public ResponseEntity endHackathon(){
        hackathonService.endHackathon();
        return ResponseEntity.status(200).body("Hackathon Ended Successfully");
    }
}
