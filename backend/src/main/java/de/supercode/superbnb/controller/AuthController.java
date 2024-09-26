package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.auth.AuthRegDTO;
import de.supercode.superbnb.dtos.user.UserShortResponseDTO;
import de.supercode.superbnb.entities.person.User;
import de.supercode.superbnb.services.AuthentificationService;
import de.supercode.superbnb.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    AuthentificationService authentificationService;
    UserService userService;

    public AuthController(AuthentificationService authentificationService, UserService userService) {
        this.authentificationService = authentificationService;
        this.userService = userService;
    }


    @PostMapping("/login") //login durch BasicAuth im header
    public UserShortResponseDTO login(Authentication authentication) {
        return userService.getUserDetailsByLogin(authentication);
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRegDTO dto){
        return authentificationService.userRegister(dto);
    }

    @DeleteMapping("/logout")
    public void logout(HttpSession session){
        session.invalidate();
    }

}
