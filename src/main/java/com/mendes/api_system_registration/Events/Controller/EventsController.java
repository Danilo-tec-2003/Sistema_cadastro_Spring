package com.mendes.api_system_registration.Events.Controller;

import com.mendes.api_system_registration.Events.DTO.EventsDTO;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Events.Service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @GetMapping("/all")
    public ResponseEntity<List> getAllEvents() {
        List<EventsDTO> getEvents = eventsService.getAllEvents();
        if (!getEvents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(getEvents);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventsDTO> findEventsById(@PathVariable Long id) {
        EventsDTO eventById = eventsService.findEventsById(id);

            if (eventById != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(eventById);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }

    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvent( @RequestBody EventsDTO eventsDTO) {
        EventsDTO newEvent =  eventsService.createEvent(eventsDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(" Usuario Criado com Sucesso: " + newEvent.getNameEvent() + " (ID: " + newEvent.getId());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity <String> editEvent(@PathVariable Long id, @RequestBody EventsDTO eventDetails) {
       EventsDTO editEventById =  eventsService.editEvent(id, eventDetails);
       if (eventsService.findEventsById(id) != null) {
           return ResponseEntity.status(HttpStatus.CREATED)
                   .body(" Usuario com o ID: " + id + "Foi alterado com Sucesso.");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(" ID: " + id + " Nao encontrado. ");
       }
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<String> deleteEventById(@PathVariable long id) {
       if (eventsService.findEventsById(id) != null) {
           eventsService.deleteEventById(id);
           return ResponseEntity.ok(" Usuario com ID: " + id + "Deletado com sucesso.");
       } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body(" Usuario com o ID: " + id + " Nao foi encontrado.");
       }

    }

}
