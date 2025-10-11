package com.mendes.api_system_registration.Events.Service;


import com.mendes.api_system_registration.Events.DTO.EventsDTO;
import com.mendes.api_system_registration.Events.Mapper.EventsMapper;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Events.Repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private EventsMapper eventsMapper;


    public List<EventsDTO> getAllEvents() {
        List<EventsModel> events = eventsRepository.findAll();
        return events.stream()
                .map(eventsMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EventsDTO findEventsById (Long id) {
        Optional<EventsModel> eventById = eventsRepository.findById(id);
        return eventById.map(eventsMapper::toDTO).orElse(null);
    }

    public EventsDTO createEvent (EventsDTO eventsDTO) {
       EventsModel eventsModel = eventsMapper.toEntity(eventsDTO);
       EventsModel savedEvent = eventsRepository.save(eventsModel);
       return eventsMapper.toDTO(savedEvent);
    }

    public void deleteEventById(@PathVariable long id) {
         eventsRepository.deleteById(id);
    }

    public EventsDTO editEvent ( Long id, EventsDTO eventDetails) {
        Optional<EventsModel> existingEvent = eventsRepository.findById(id);

        if (existingEvent.isPresent()) {
            EventsModel updateEvent = eventsMapper.toEntity(eventDetails);
            updateEvent.setId(id);
            EventsModel savedEvent = eventsRepository.save(updateEvent);

            return eventsMapper.toDTO(savedEvent);

        }
        return null;
    }

}
