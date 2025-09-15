package com.mendes.api_system_registration.Events.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {

    @GetMapping("/all")
    public String getAllEvents() {
        return "Retornando todos os Eventos.";
    }

    @GetMapping("/{id}")
    public String getEventByEvent() {
        return "Retornando Evento por ID.";
    }

    @PostMapping("/create")
    public String createEvent() {
        return "Registrando um novo Evento.";
    }

    @PutMapping("/{id}")
    public String updateEvent() {
        return "Alterando o Evento por ID";
    }

    @DeleteMapping("/{id}")
    public String deleteEvent() {
        return "Deletando o Evento por ID";
    }

}
