package br.com.rodrigo.ms.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Value("${greeting}")
    private String greeting;
    
    @GetMapping
    public ResponseEntity<String> index() {
        return ResponseEntity.ok(greeting);
    }
    
}
