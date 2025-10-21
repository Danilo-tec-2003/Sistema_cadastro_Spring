package com.mendes.api_system_registration.Events.Service;

import com.mendes.api_system_registration.Events.DTO.EventsDTO;
import com.mendes.api_system_registration.Events.DTO.EventsSummaryDTO;
import com.mendes.api_system_registration.Events.Mapper.EventsMapper;
import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Events.Repository.EventsRepository;
import com.mendes.api_system_registration.exception.ResourceNotFoundException; // Assumindo que a exceção existe
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço responsável pela lógica de negócio relacionada aos Eventos.
 * Esta classe orquestra as operações entre o controller e o repositório.
 */
@Service
public class EventsService {

    private final EventsRepository eventsRepository;
    private final EventsMapper eventsMapper;

    /**
     * Constrói o EventsService com as dependências necessárias.
     * Esta é a melhor prática para injeção de dependência (Injeção via Construtor).
     *
     * @param eventsRepository O repositório para acesso aos dados do evento.
     * @param eventsMapper     O mapper para converter entre entidades e DTOs.
     */
    public EventsService(EventsRepository eventsRepository, EventsMapper eventsMapper) {
        this.eventsRepository = eventsRepository;
        this.eventsMapper = eventsMapper;
    }

    /**
     * Busca uma lista resumida de todos os eventos cadastrados.
     *
     * @return Uma lista de {@link EventsSummaryDTO} representando todos os eventos. A lista estará vazia se nenhum evento for encontrado.
     */
    public List<EventsSummaryDTO> getAllEvents() {
        // 1. Busca todas as entidades de evento no banco.
        List<EventsModel> events = eventsRepository.findAll();

        // 2. Converte cada EventsModel para EventsSummaryDTO usando o mapper.
        return events.stream()
                .map(eventsMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um evento específico (e seus detalhes) pelo seu ID único.
     *
     * @param id O ID do evento a ser encontrado.
     * @return Um {@link EventsDTO} com os dados detalhados do evento.
     * @throws ResourceNotFoundException se nenhum evento for encontrado com o ID fornecido.
     */
    public EventsDTO findEventsById(Long id) {
        // 1. Busca o evento no banco ou lança uma exceção se não for encontrado.
        EventsModel event = eventsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));

        // 2. Converte a entidade encontrada para o DTO detalhado e o retorna.
        return eventsMapper.toDTO(event);
    }

    /**
     * Cria um novo evento com base nos dados fornecidos e o persiste no banco de dados.
     *
     * @param eventsDTO O DTO contendo os dados para o novo evento.
     * @return O {@link EventsDTO} do evento recém-criado, incluindo o ID gerado.
     */
    public EventsDTO createEvent(EventsDTO eventsDTO) {
        // 1. Converte o DTO em uma entidade para persistência.
        // (O mapper ignora a lista de 'users' no DTO, o que está correto para criação).
        EventsModel eventsModel = eventsMapper.toEntity(eventsDTO);

        // 2. Salva a nova entidade no banco de dados.
        EventsModel savedEvent = eventsRepository.save(eventsModel);

        // 3. Converte a entidade salva de volta para um DTO detalhado e o retorna.
        return eventsMapper.toDTO(savedEvent);
    }

    /**
     * Atualiza as informações de um evento existente com base no seu ID.
     * <p>
     * <strong>CORREÇÃO DE BUG:</strong> Esta implementação "carrega e salva" (load-and-save).
     * Ela primeiro busca o evento existente, atualiza seus campos com os dados do DTO,
     * e então o salva. Isso preserva relacionamentos (como a lista de usuários)
     * que não são gerenciados por este DTO.
     *
     * @param id           O ID do evento a ser atualizado.
     * @param eventDetails Um DTO contendo os novos dados para o evento.
     * @return O {@link EventsDTO} atualizado.
     * @throws ResourceNotFoundException se o evento não for encontrado.
     */
    public EventsDTO editEvent(Long id, EventsDTO eventDetails) {
        // 1. Busca o evento existente no banco ou lança uma exceção se não for encontrado.
        EventsModel existingEvent = eventsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + id));

        // 2. Atualiza os campos da entidade existente com os dados do DTO.
        //    Não mexemos na lista 'users' aqui, pois ela é gerenciada pelo 'User'.
        existingEvent.setNameEvent(eventDetails.getNameEvent());
        existingEvent.setDateEvent(eventDetails.getDateEvent());
        existingEvent.setDescriptionEvent(eventDetails.getDescriptionEvent());

        // 3. Salva a entidade atualizada de volta no banco.
        EventsModel savedEvent = eventsRepository.save(existingEvent);

        // 4. Converte a entidade atualizada para DTO e a retorna.
        return eventsMapper.toDTO(savedEvent);
    }

    /**
     * Deleta um evento do banco de dados pelo seu ID.
     *
     * @param id O ID do evento a ser deletado.
     * @throws ResourceNotFoundException se o evento não for encontrado.
     */
    public void deleteEventById(Long id) {
        // 1. Verifica se o evento existe antes de tentar deletar.
        if (!eventsRepository.existsById(id)) {
            throw new ResourceNotFoundException("Evento não encontrado com ID: " + id);
        }

        // 2. Deleta o evento.
        eventsRepository.deleteById(id);
    }
}