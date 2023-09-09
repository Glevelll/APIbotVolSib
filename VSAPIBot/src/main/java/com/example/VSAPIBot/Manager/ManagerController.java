package com.example.VSAPIBot.Manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
    private final ManagerRepository managerRepository;

    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/{login}")
    @ResponseBody
    public ResponseEntity<Manager> getUserByLogin(@PathVariable("login") Long login) {
        try {
            Manager manager = managerRepository.findByLogin(login);
            if (manager != null) {
                return new ResponseEntity<>(manager, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("Failed to get user by login: {} ", login, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Void> createManager(@RequestBody Manager manager) {
        try {
            Manager existingManager = managerRepository.findByLogin(manager.getLogin());
            if (existingManager != null) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            } else {
                managerRepository.save(manager);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            logger.error("Failed to create manager ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
