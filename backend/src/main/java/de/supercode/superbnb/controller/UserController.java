package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.auth.AuthAdminRegDTO;
import de.supercode.superbnb.dtos.user.UserDetailsResponseDTO;
import de.supercode.superbnb.dtos.user.UserListDTO;
import de.supercode.superbnb.dtos.user.UserUpdateRequestDTO;
import de.supercode.superbnb.services.AuthentificationService;
import de.supercode.superbnb.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;
    AuthentificationService authentificationService;

    public UserController(UserService userService, AuthentificationService authentificationService) {
        this.userService = userService;
        this.authentificationService = authentificationService;
    }



    //GET /api/v1/users: Liste aller Benutzer anzeigen (nur für Administratoren)
    @GetMapping
    public ResponseEntity<List<UserListDTO>> getUserList(Principal principal) {
        return ResponseEntity.ok(userService.getUserList(principal.getName()));
    }

    //GET /api/users/details/{id}: Details eines Benutzers anzeigen (nur für Administratoren) oder sich selbst (optional)
    @GetMapping("/details/{id}")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@PathVariable long id, Principal adminDetails) {
        return ResponseEntity.ok(userService.getUserDetails(id, adminDetails.getName()));
    }


    //POST /api/users: Einen neuen Benutzer anlegen (nur für Administratoren)
    @PostMapping
    public ResponseEntity<Void> creatNewUserByAdmin(@RequestBody AuthAdminRegDTO newUserDTO) {
        if (authentificationService.userRegisterByAdmin(newUserDTO)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }

    //PUT /api/users: Bearbeiten eines benutzers (nur Administrator) oder sich selbst Benutzers
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateRequestDTO updatedUserDTO, Principal adminDetails) {
        if (userService.updateUser(updatedUserDTO, adminDetails.getName())) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
    }




    //DELETE /api/users/delete/{id}: Einen Benutzer löschen (nur für Administratoren)


}
