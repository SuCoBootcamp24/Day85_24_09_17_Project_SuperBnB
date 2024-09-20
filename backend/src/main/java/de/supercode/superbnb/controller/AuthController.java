package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.auth.AuthDTO;
import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.services.AuthentificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    AuthentificationService authentificationService;

    public AuthController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthDTO dto, HttpServletRequest request, HttpServletResponse response){
        authentificationService.login(dto, request, response);
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRegDTO dto){
        return authentificationService.UserRegister(dto);
    }

}
