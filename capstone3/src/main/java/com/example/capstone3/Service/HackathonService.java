package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Hackathon;
import com.example.capstone3.Model.Member;
import com.example.capstone3.Model.Request;
import com.example.capstone3.Model.Team;
import com.example.capstone3.Repository.HackathonRepository;
import com.example.capstone3.Repository.MemberRepository;
import com.example.capstone3.Repository.RequestRepository;
import com.example.capstone3.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HackathonService {
    private final HackathonRepository hackathonRepository;
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final RequestRepository requestRepository;
   // private final TeamService teamService;
    public List<Hackathon> getAllHackathon(){
        return hackathonRepository.findAll();
    }

    public void addHackathon(Hackathon hackathon){
        hackathonRepository.save(hackathon);
    }

    public void updateHackathon(Integer id,Hackathon hackathon){
        Hackathon oldHackathon=hackathonRepository.findHackathonById(id);
        if(oldHackathon==null){
            throw new ApiException("Hackathon not found");
        }
        oldHackathon.setName(hackathon.getName());
        oldHackathon.setName(hackathon.getName());
        oldHackathon.setMax(hackathon.getMax());
        oldHackathon.setPrize(hackathon.getPrize());
        oldHackathon.setMinAge(hackathon.getMinAge());
        oldHackathon.setMaxAge(hackathon.getMaxAge());
        oldHackathon.setStartDate(hackathon.getStartDate());
        oldHackathon.setEndDate(hackathon.getEndDate());
        oldHackathon.setCompanyName(hackathon.getCompanyName());
        oldHackathon.setCity(hackathon.getCity());
        oldHackathon.setIsOnline(hackathon.getIsOnline());

        hackathonRepository.save(oldHackathon);
    }

    public void deleteHackathon(Integer id){
        Hackathon hackathon=hackathonRepository.findHackathonById(id);
        if(hackathon==null){
            throw new ApiException("Hackathon not found");
        }
        for(Team t:hackathon.getTeams()){
            t.setHackathon(null);
            teamRepository.save(t);
        }
        hackathonRepository.delete(hackathon);
    }
    public void assignTeamToHackathon(Integer hackathonId,Integer teamId){
        Hackathon hackathon=hackathonRepository.findHackathonById(hackathonId);
        Team team=teamRepository.findTeamById(teamId);
        if(hackathon==null||team==null){
            throw new ApiException("Hackathon or team not found");
        }
        if(hackathon.getMax()<team.getMaxCap()){
            throw new ApiException(" Maximum Number Of Members Is : "+hackathon.getMax());
        }

        Set<Team>s=hackathon.getTeams();
        s.add(team);
        hackathon.setTeams(s);

        hackathonRepository.save(hackathon);
        teamRepository.save(team);
    }

    //--- endpoint 1  اند بوينت تعرض الجائزه الاعلى ---
    public Hackathon getHighestPrize() {
            List<Hackathon> hackathons=hackathonRepository.findAll();
            if(hackathons.isEmpty()){
                throw new ApiException("No Hackathons");
            }
            Hackathon hackathon=hackathons.get(0);
            for (int i=0;i<hackathons.size();i++){
                if(hackathons.get(i).getPrize()>hackathon.getPrize()){
                    hackathon=hackathons.get(i);
                }
            }
            return hackathon;
    }


    //--- endpoint 2  اند بوينت تعرض الهاكثونات تبدا قبل تاريخ معين ---
    public List<Hackathon> getHackathonsStartingBeforeDate(LocalDate targetDate) {
        if(hackathonRepository.findHackathonsStartingBeforeDate(targetDate).isEmpty()){
            throw new ApiException("sorry there is no hackathons before this date!");
        }
        return hackathonRepository.findHackathonsStartingBeforeDate(targetDate);
    }

    //--- endpoint 3  اند بوينت تعرض الهاكثونات تبدا بعد تاريخ معين ---

    public List<Hackathon> getHackathonsStartingAfterDate(LocalDate targetDate) {
        if(hackathonRepository.findHackathonsStartingAfterDate(targetDate).isEmpty()){
            throw new ApiException("sorry there is no hackathons after this date!");
        }
        return hackathonRepository.findHackathonsStartingAfterDate(targetDate);
    }


    //--- endpoint 4  اند بوينت تعرض الهاكثونات تبدا بين تاريخين معينه ---
    public List<Hackathon> findHackathonsByStartDateBetweenAndEndDate(LocalDate startDate, LocalDate endDate) {
        if(hackathonRepository.findHackathonsByStartDateBetweenAndEndDate(startDate, endDate).isEmpty()){
            throw new ApiException("sorry there is no hackathons in this period!");
        }
        return hackathonRepository.findHackathonsByStartDateBetweenAndEndDate(startDate, endDate);
    }


    //--- endpoint 5 اند بوينت تعرض الهاكثونات للاطفال ---
    public List<Hackathon> findHackathonChildren() {
        if(hackathonRepository.findHackathonChildren().isEmpty()){
            throw new ApiException("sorry there is no hackathon ads for children until now");
        }
        return hackathonRepository.findHackathonChildren();
    }

    //--- endpoint 6  اند بوينت تعرض الهاكثونات للمراهقين ---
    public List<Hackathon> findHackathonsForTeens() {
        if(hackathonRepository.findHackathonsForTeens().isEmpty()){
            throw new ApiException("sorry there is no hackathon ads for teenagers until now");
        }
        return hackathonRepository.findHackathonsForTeens();
    }


    //--- endpoint 7  اند بوينت تعرض الهاكثونات للبالغين  ---
    public List<Hackathon> findHackathonsForAdults() {
        if(hackathonRepository.findHackathonsForAdults().isEmpty()){
            throw new ApiException("sorry there is no hackathon ads for adults until now");
        }
        return hackathonRepository.findHackathonsForAdults();
    }


    //--- endpoint 8  اند بوينت تعرض الهاكثونات لمجال معين  ---
    public List<Hackathon> findHackathonByField(String field) {
        if (hackathonRepository.findHackathonByField(field).isEmpty() ) {
            throw new ApiException("sorry there is no hackathon in this field");
        }
        return hackathonRepository.findHackathonByField(field);
    }

    //--- endpoint 9  اند بوينت تعرض هاكثونات لجهه معينه  ---
    public List<Hackathon> findHackathonByCompanyName(String companyName) {
        if (hackathonRepository.findHackathonByCompanyName(companyName).isEmpty()) {
            throw new ApiException("sorry there is no hackathon in this company");
        }
        return hackathonRepository.findHackathonByCompanyName(companyName);
    }


    //--- endpoint 10  اند بوينت تعرض هاكثونات اونلاين  ---
    public List<Hackathon> findOnlineHackathons() {
        if (hackathonRepository.findHackathonsByIsOnlineTrue().isEmpty()) {
            throw new ApiException("sorry there is no hackathon online in the mean time");
        }
        return hackathonRepository.findHackathonsByIsOnlineTrue();
    }

    //--- endpoint 11  اند بوينت تعرض هاكثونات مدينه معينه  ---

    public List<Hackathon> findHackathonsByCity(String city) {
        if (hackathonRepository.findHackathonsByCity(city).isEmpty()) {
            throw new ApiException("sorry there is no hackathon in this city");
        }
        return hackathonRepository.findHackathonsByCity(city);
    }


    //--- endpoint 12  اند بوينت ابحث عن الهاكثون باسمه  ---
    public Hackathon findHackathonByName(String name) {
        Hackathon hackathon = hackathonRepository.findHackathonByName(name);
        if (hackathon == null) {
            throw new ApiException("Hackathon with name : " + name + " is not found");
        }
        return hackathon;
    }

//    --- endpoint 13
    public void endHackathon(){
        List<Hackathon> hackathons=hackathonRepository.findHackathonByEndDate(LocalDate.now());
        for (Hackathon h:hackathons){
            for(Team t: h.getTeams()){
                for (Member m:t.getMembers()){
                    m.setTeam(null);
                    m.setRequests(null);

                    teamRepository.save(t);
                    memberRepository.save(m);
                }
                for (Request r:t.getRequest()){
                    r.setTeam(null);
                    r.setMember(null);
                    requestRepository.save(r);
                    requestRepository.delete(r);
                }
                t.setProject(null);
                t.setRequest(null);
                t.setMembers(null);
               t.setHackathon(null);
               teamRepository.save(t);
               teamRepository.delete(t);
           }
            h.setTeams(null);
            hackathonRepository.save(h);

        }
    }
}
