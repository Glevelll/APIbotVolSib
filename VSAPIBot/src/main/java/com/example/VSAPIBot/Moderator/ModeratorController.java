package com.example.VSAPIBot.Moderator;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/moderator")
public class ModeratorController {

    @Autowired
    private ModeratorRepository moderatorRepository;

    @GetMapping("/{login}")
    @ResponseBody
    public ResponseEntity<Moderator> getModerator(@PathVariable String login) {
        Moderator moderator = moderatorRepository.findById(login)
                .orElseThrow(() -> new EntityNotFoundException("Moderator not found with login: " + login));
        return ResponseEntity.ok(moderator);
    }
}