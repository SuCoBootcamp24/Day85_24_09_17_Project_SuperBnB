package de.supercode.superbnb.controller;

import de.supercode.superbnb.dtos.favorite.FavoriteListDTO;
import de.supercode.superbnb.entities.Property;
import de.supercode.superbnb.services.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/favorite")
public class FavoriteController {

    PropertyService propertyService;



    public FavoriteController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    //GET /api/favorites: Liste aller favoriten anzeigen ==== (USER Only)
    @GetMapping
    public ResponseEntity<List<FavoriteListDTO>> getFavorites(Principal principal) {
        return ResponseEntity.ok(propertyService.getFavorites(principal.getName()));
    }

    //POST /api/favorites: Ein Ferienwohnung zu favoriten hinzufügen ====(USER Only)
    @PostMapping
    public ResponseEntity<Void> addToFavorites(@RequestParam long id, Principal principal) {
        propertyService.addToFavorites(id, principal.getName());
        return ResponseEntity.ok().build();
    }

    //DELETE /api/favorites/{id}: Ein Ferienwohnung aus favoriten entfernen ====(USER Only)


}
