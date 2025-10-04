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
    public EventsModel createEvent( @RequestBody EventsModel event) {
        return eventsService.createEvent(event);
    }

    @PutMapping("/edit/{id}")
    public EventsModel editEvent(@PathVariable Long id, @RequestBody EventsModel eventDetails) {
        return eventsService.editEvent(id,eventDetails);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventsService.deleteEventById(id);
    }

}
