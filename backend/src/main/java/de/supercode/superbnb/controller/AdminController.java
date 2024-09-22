package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.user.UserListDTO;
import de.supercode.superbnb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AdminController {

    UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }



    //GET /api/v1/users: Liste aller Benutzer anzeigen (nur für Administratoren)
    @GetMapping
    public ResponseEntity<List<UserListDTO>> getUserList(Principal principal) {
        return ResponseEntity.ok(userService.getUserList(principal.getName()));
    }

    //POST /api/users: Einen neuen Benutzer anlegen (nur für Administratoren)


    //DELETE /api/users/{id}: Einen Benutzer löschen (nur für Administratoren)


}
