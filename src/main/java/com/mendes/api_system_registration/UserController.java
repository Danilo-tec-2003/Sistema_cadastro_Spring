package com.mendes.api_system_registration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    @GetMapping("/boasvindas")
    public String boasvindas() {
        return "Boas vinda, esse é o meu  controlador.";
    }

}




