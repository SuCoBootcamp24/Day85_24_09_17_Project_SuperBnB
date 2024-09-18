package de.supercode.superbnb.controller;

import de.supercode.superbnb.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //GET /api/users: Liste aller Benutzer anzeigen (nur für Administratoren)


    //POST /api/users: Einen neuen Benutzer anlegen (nur für Administratoren)


    //DELETE /api/users/{id}: Einen Benutzer löschen (nur für Administratoren)


}
