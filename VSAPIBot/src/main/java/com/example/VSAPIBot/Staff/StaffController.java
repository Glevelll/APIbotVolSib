package com.example.VSAPIBot.Staff;

import com.example.VSAPIBot.Manager.Manager;
import com.example.VSAPIBot.Project.Project;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;
    private final Logger logger = LoggerFactory.getLogger(StaffController.class);

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping("/{state}/{id_manager}")
    @ResponseBody
    public ResponseEntity<List<Staff>> getStaffByStateAndIdManager(
            @PathVariable("state") String state,
            @PathVariable("id_manager") Long idManager) {
        try {
            List<Staff> staffList = staffRepository.findByStateAndIdManager(state, idManager);
            if (!staffList.isEmpty()) {
                return new ResponseEntity<>(staffList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while getting staff by state and idManager: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{projectId}")
    @ResponseBody
    public ResponseEntity<List<Staff>> getStaffByIdProject(
            @PathVariable("idProject") Project projectId) {
        try {
            List<Staff> staffList = staffRepository.findByIdProject(projectId);
            if (!staffList.isEmpty()) {
                return new ResponseEntity<>(staffList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while getting staff by state and idManager: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{projectId}")
    @ResponseBody
    public ResponseEntity<Staff> updateStaffByProjectId(@PathVariable("idProject") Project projectId, @RequestBody Staff staff) {
        try {
            Staff existingStaff = staffRepository.findByProjectId(projectId);
            if (existingStaff != null) {
                existingStaff.setManagerId(staff.getManagerId());
                existingStaff.setUserId(staff.getUserId());
                existingStaff.setComment(staff.getComment());
                existingStaff.setState(staff.getState());
                existingStaff.setProjectId(staff.getProjectId());
                Staff updatedStaff = staffRepository.save(existingStaff);
                return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Error while updating staff by project id: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        try {
            Staff createdStaff = staffRepository.save(staff);
            return new ResponseEntity<>(createdStaff, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating staff: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}