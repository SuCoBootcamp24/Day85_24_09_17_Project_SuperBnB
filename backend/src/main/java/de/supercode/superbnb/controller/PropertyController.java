package de.supercode.superbnb.controller;

import de.supercode.superbnb.services.PropertyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/v1/Properties")
public class PropertyController {

    PropertyService propertyService;


    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }
}
