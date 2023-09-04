package com.example.VSAPIBot.Manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerRepository managerRepository;

    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    // Метод для получения информации о менеджере по его логину
    @GetMapping("/{login}")
    @ResponseBody
    public ResponseEntity<Manager> getUserByLogin(@PathVariable("login") String login) {
        Manager manager = managerRepository.findByLogin(login);
        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Метод для добавления нового менеджера
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Void> createManager(@RequestBody Manager manager) {
        Manager existingManager = managerRepository.findByLogin(manager.getLogin());
        if (existingManager != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            managerRepository.save(manager);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
