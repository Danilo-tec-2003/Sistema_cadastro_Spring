package com.mendes.api_system_registration.Events.Controller; // Recomendo renomear o pacote para ...Event.Controller

import com.mendes.api_system_registration.Events.DTO.EventsDTO; // DTO Renomeado
import com.mendes.api_system_registration.Events.DTO.EventsSummaryDTO; // Assumindo que você criará um DTO Resumido, assim como fez para User
import com.mendes.api_system_registration.Events.DTO.EventsSummaryDTO;
import com.mendes.api_system_registration.Events.Service.EventsService; // Service Renomeado
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller REST para gerenciar os recursos de Eventos.
 * <p>
 * Expõe endpoints para realizar operações CRUD (Criar, Ler, Atualizar, Deletar)
 * em eventos, seguindo as convenções da arquitetura RESTful.
 */
@RestController
@RequestMapping("/events") // Define a rota base para todos os endpoints deste controller
public class EventsController {

    private final EventsService eventsService; // Service renomeado

    /**
     * Constrói o EventController com a injeção do EventService.
     * A injeção via construtor é a prática recomendada pelo Spring.
     *
     * @param eventService O serviço que contém a lógica de negócio para eventos.
     */
    public EventsController(EventsService eventService) {
        this.eventsService = eventService;
    }

    /**
     * Endpoint para buscar todos os eventos cadastrados (em formato resumido).
     * <p>
     * <strong>GET /events</strong>
     *
     * @return Um {@link ResponseEntity} com status 200 OK e a lista de {@link EventsSummaryDTO} no corpo.
     * A lista retornará vazia (status 200) se não houver eventos.
     */
    @GetMapping
    public ResponseEntity<List<EventsSummaryDTO>> getAllEvents() {
        List<EventsSummaryDTO> events = eventsService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    /**
     * Endpoint para buscar um evento específico pelo seu ID.
     * <p>
     * <strong>GET /events/{id}</strong>
     *
     * @param id O ID do evento a ser buscado.
     * @return Um {@link ResponseEntity} com status 200 OK e o {@link EventsDTO} detalhado no corpo.
     * Lançará uma exceção (tratada como 404 Not Found) se o evento não existir.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EventsDTO> findEventById(@PathVariable Long id) {
        EventsDTO event = eventsService.findEventsById(id);
        return ResponseEntity.ok(event);
    }

    /**
     * Endpoint para criar um novo evento.
     * <p>
     * <strong>POST /events</strong>
     *
     * @param eventDTO O {@link EventsDTO} contendo os dados do novo evento, enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com status 201 Created. O corpo contém o
     * evento recém-criado e o cabeçalho 'Location' contém a URL para acessá-lo.
     */
    @PostMapping
    public ResponseEntity<EventsDTO> createEvent(@Valid @RequestBody EventsDTO eventDTO) {
        EventsDTO newEvent = eventsService.createEvent(eventDTO);

        // Constrói a URI do novo recurso criado para retornar no header 'Location'
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEvent.getId())
                .toUri();

        return ResponseEntity.created(location).body(newEvent);
    }

    /**
     * Endpoint para atualizar um evento existente.
     * <p>
     * <strong>PUT /events/{id}</strong>
     *
     * @param id           O ID do evento a ser atualizado.
     * @param eventDetails O {@link EventsDTO} com os novos dados, enviado no corpo da requisição.
     * @return Um {@link ResponseEntity} com status 200 OK e o {@link EventsDTO} atualizado no corpo.
     * Lançará uma exceção (tratada como 404 Not Found) se o evento não existir.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EventsDTO> editEvent(@PathVariable Long id, @Valid @RequestBody EventsDTO eventDetails) {
        EventsDTO updatedEvent = eventsService.editEvent(id, eventDetails);
        return ResponseEntity.ok(updatedEvent);
    }

    /**
     * Endpoint para deletar um evento pelo seu ID.
     * <p>
     * <strong>DELETE /events/{id}</strong>
     *
     * @param id O ID do evento a ser deletado.
     * @return Um {@link ResponseEntity} com status 204 No Content (sem corpo), indicando sucesso.
     * Lançará uma exceção (tratada como 404 Not Found) se o evento não existir.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable Long id) {
        eventsService.deleteEventById(id);
        return ResponseEntity.noContent().build();
    }
}