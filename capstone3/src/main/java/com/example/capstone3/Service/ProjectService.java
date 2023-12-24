package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.DTO.ProjectDTO;
import com.example.capstone3.Model.Project;
import com.example.capstone3.Model.Team;
import com.example.capstone3.Repository.ProjectRepository;
import com.example.capstone3.Repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public void addProject(ProjectDTO projectDTO){
        Team team=teamRepository.findTeamById(projectDTO.getTeam_id());
        if(team==null){
            throw new ApiException("Team not found you cant creat project");
        }
        Project project=new Project(null,projectDTO.getName(), projectDTO.getIdea(),team);
        projectRepository.save(project);
    }

    public void updateProject(ProjectDTO projectDTO,Integer project_id){
        Project oldProject=projectRepository.findProjectById(project_id);
        if(oldProject==null){
            throw new ApiException("Project Not found");
        }
        oldProject.setName(projectDTO.getName());
        oldProject.setIdea(projectDTO.getIdea());
        projectRepository.save(oldProject);
    }

    public void deleteProject(Integer project_id){
        Project project=projectRepository.findProjectById(project_id);
        if(project==null){
            throw new ApiException("Project Not Found");
        }
        Team team=teamRepository.findTeamById(project.getTeam().getId());
        team.setProject(null);
        teamRepository.save(team);
        projectRepository.delete(project);
    }

}
