package com.mendes.api_system_registration.Events.Controller;

import com.mendes.api_system_registration.Events.DTO.EventsDTO;
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
    public List<EventsDTO> getAllEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventsDTO findEventsById(@PathVariable Long id) {
        return eventsService.findEventsById(id);
    }

    @PostMapping("/create")
    public EventsDTO createEvent( @RequestBody EventsDTO eventsDTO) {
        return eventsService.createEvent(eventsDTO);
    }

    @PutMapping("/edit/{id}")
    public EventsDTO editEvent(@PathVariable Long id, @RequestBody EventsDTO eventDetails) {
        return eventsService.editEvent(id, eventDetails);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventsService.deleteEventById(id);
    }

}
