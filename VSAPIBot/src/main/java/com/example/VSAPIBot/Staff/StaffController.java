package com.example.VSAPIBot.Staff;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class StaffController {
    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    // Метод для получения информации о сотруднике по его состоянию (state)
    @GetMapping("/state/{state}")
    public ResponseEntity<List<Staff>> getStaffByState(@PathVariable("state") String state) {
        List<Staff> staffList = staffRepository.findByState(state);
        if (!staffList.isEmpty()) {
            return new ResponseEntity<>(staffList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Метод для изменения данных сотрудника по идентификатору проекта (id_project)
    @PutMapping("/{idProject}")
    public ResponseEntity<Staff> updateStaffByProjectId(@PathVariable("idProject") Long idProject, @RequestBody Staff staff) {
        Staff existingStaff = staffRepository.findByProjectId(idProject);
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
    }
}