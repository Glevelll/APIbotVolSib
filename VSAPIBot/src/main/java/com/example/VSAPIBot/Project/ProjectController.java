package com.example.VSAPIBot.Project;

import com.example.VSAPIBot.Manager.Manager;
import org.codehaus.plexus.resource.loader.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));
            return ResponseEntity.ok().body(project);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error while fetching project by id ", ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("Internal server error ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{state}/{infoManager}")
    @ResponseBody
    public ResponseEntity<Project> getProjectByStateAndInfoManager(
            @PathVariable String state,
            @PathVariable Manager infoManager) {
        try {
            Project project = projectRepository.findByStateAndInfoManager(state, infoManager)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with state: " + state
                            + " and infoManager: " + infoManager));
            return ResponseEntity.ok().body(project);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error while fetching project by state and infoManager ", ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("Internal server error ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        try {
            Project createdProject = projectRepository.save(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
        } catch (Exception ex) {
            logger.error("Error while creating project ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

            project.setTitle(projectDetails.getTitle());
            project.setType(projectDetails.getType());
            project.setDescription(projectDetails.getDescription());
            project.setRequirements(projectDetails.getRequirements());
            project.setState(projectDetails.getState());
            project.setComment(projectDetails.getComment());
            project.setInfoModer(projectDetails.getInfoModer());

            Project updatedProject = projectRepository.save(project);
            return ResponseEntity.ok().body(updatedProject);
        } catch (ResourceNotFoundException ex) {
            logger.error("Error while updating project - Project not found ", ex);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("Error while updating project ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}