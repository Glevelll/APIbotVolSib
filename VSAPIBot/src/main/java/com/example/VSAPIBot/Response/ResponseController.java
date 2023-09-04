package com.example.VSAPIBot.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/response")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Response> createResponse(@RequestBody Response response) {
        Response createdResponse = responseRepository.save(response);
        return new ResponseEntity<>(createdResponse, HttpStatus.CREATED);
    }
}
