package com.mendes.api_system_registration.Events.Controller;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Events.Service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/all")
    public List<EventsModel> getAllEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventsModel findEventsById(@PathVariable Long id) {
        return eventsService.findEventsById(id);
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
