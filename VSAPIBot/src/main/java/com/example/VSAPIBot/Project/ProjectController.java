package com.example.VSAPIBot.Project;

import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

//    @GetMapping("/{id}")
//    @ResponseBody
//    public Project getProjectById(@PathVariable Long id) throws ResourceNotFoundException {
//        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
//    }


    @GetMapping("/{state}")
    @ResponseBody
    public Project getProjectByState(@PathVariable String state) throws ResourceNotFoundException {
        return projectRepository.findByState(state).orElseThrow(() -> new ResourceNotFoundException("Project not found with state: " + state));
    }

    @PostMapping
    @ResponseBody
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Project updateProject(@PathVariable Long id, @RequestBody Project projectDetails) throws ResourceNotFoundException {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        project.setTitle(projectDetails.getTitle());
        project.setType(projectDetails.getType());
        project.setDescription(projectDetails.getDescription());
        project.setRequirements(projectDetails.getRequirements());
        project.setState(projectDetails.getState());
        project.setComment(projectDetails.getComment());
        project.setInfoModer(projectDetails.getInfoModer());

        return projectRepository.save(project);
    }
}