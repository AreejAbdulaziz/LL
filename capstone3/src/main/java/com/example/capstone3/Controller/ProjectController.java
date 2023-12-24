package com.example.capstone3.Controller;

import com.example.capstone3.DTO.ProjectDTO;
import com.example.capstone3.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    @GetMapping("/get")
    public ResponseEntity getAllProjects(){
        return ResponseEntity.status(200).body(projectService.getAllProjects());
    }
    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody@Valid ProjectDTO projectDTO){
        projectService.addProject(projectDTO);
        return ResponseEntity.status(200).body("Project Added");
    }
    @PutMapping("/update/{project_id}")
    public ResponseEntity updateProject(@RequestBody@Valid ProjectDTO projectDTO,@PathVariable Integer project_id){
        projectService.updateProject(projectDTO, project_id);
        return ResponseEntity.status(200).body("Project Updated");
    }
    @DeleteMapping("/delete/{project_id}")
    public ResponseEntity deleteProject(@PathVariable Integer project_id){
        projectService.deleteProject(project_id);
        return ResponseEntity.status(200).body("Project Deleted");
    }
}
