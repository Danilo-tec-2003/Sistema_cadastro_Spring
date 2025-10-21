package com.mendes.api_system_registration.Users.Service;

import com.mendes.api_system_registration.Events.Model.EventsModel;
import com.mendes.api_system_registration.Events.Repository.EventsRepository;
import com.mendes.api_system_registration.Users.DTO.UsersDTO;
import com.mendes.api_system_registration.Users.DTO.UserSummaryDTO;
import com.mendes.api_system_registration.Users.Mapper.UserMapper;
import com.mendes.api_system_registration.Users.Model.User;
import com.mendes.api_system_registration.Users.Repository.UserRepository;
import com.mendes.api_system_registration.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço responsável pela lógica de negócio relacionada aos usuários.
 * Esta classe orquestra as operações entre o controller e o repositório.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EventsRepository eventsRepository;

    /**
     * Constrói o UserService com as dependências necessárias.
     * Esta é a melhor prática para injeção de dependência (Injeção via Construtor).
     *
     * @param userRepository   O repositório para acesso aos dados do usuário.
     * @param userMapper       O mapper para converter entre entidades e DTOs.
     * @param eventsRepository O repositório para buscar entidades de Evento.
     */
    public UserService(UserRepository userRepository, UserMapper userMapper, EventsRepository eventsRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.eventsRepository = eventsRepository;
    }

    /**
     * Busca uma lista resumida de todos os usuários cadastrados.
     *
     * @return Uma lista de {@link UserSummaryDTO} representando todos os usuários.
     */
    public List<UserSummaryDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toSummaryDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca um usuário específico pelo seu ID único.
     *
     * @param id O ID do usuário a ser encontrado.
     * @return Um {@link UsersDTO} com os dados detalhados do usuário.
     * @throws ResourceNotFoundException se nenhum usuário for encontrado com o ID fornecido.
     */
    public UsersDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
        return userMapper.toDTO(user);
    }

    /**
     * Cria um novo usuário com base nos dados fornecidos e o persiste no banco de dados.
     *
     * @param userDTO O DTO contendo os dados para o novo usuário.
     * @return O {@link UsersDTO} do usuário recém-criado, incluindo o ID gerado.
     * @throws ResourceNotFoundException se o eventId fornecido não corresponder a um evento existente.
     */
    public UsersDTO createUser(UsersDTO userDTO) {
        // 1. Converte o DTO em uma entidade.
        User user = userMapper.toEntity(userDTO);

        // 2. Lógica para associar o evento (se um eventId foi fornecido).
        if (userDTO.getEventId() != null) {
            EventsModel event = eventsRepository.findById(userDTO.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + userDTO.getEventId()));
            user.setEvents(event);
        }

        // 3. Salva a nova entidade no banco.
        User savedUser = userRepository.save(user);

        // 4. Converte a entidade salva de volta para DTO e a retorna.
        return userMapper.toDTO(savedUser);
    } // <-- A CHAVE EXTRA ESTAVA AQUI E FOI REMOVIDA.

    /**
     * Atualiza as informações de um usuário existente com base no seu ID.
     *
     * @param id          O ID do usuário a ser atualizado.
     * @param userDetails Um DTO contendo os novos dados para o usuário.
     * @return O {@link UsersDTO} atualizado.
     * @throws ResourceNotFoundException se o usuário ou o eventId (se fornecido) não forem encontrados.
     */
    public UsersDTO editUser(Long id, UsersDTO userDetails) {
        // 1. Busca o usuário existente no banco ou lança uma exceção.
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));

        // 2. Atualiza os campos da entidade existente com os dados do DTO.
        existingUser.setName(userDetails.getName());
        existingUser.setAge(userDetails.getAge());
        existingUser.setCity(userDetails.getCity());
        existingUser.setProfession(userDetails.getProfession());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setCpf(userDetails.getCpf());
        existingUser.setUrlImg(userDetails.getUrlImg());

        // 3. Lógica para atualizar a associação do evento.
        if (userDetails.getEventId() != null) {
            // Se um eventId foi fornecido, busca e atualiza o evento.
            EventsModel event = eventsRepository.findById(userDetails.getEventId())
                    .orElseThrow(() -> new ResourceNotFoundException("Evento não encontrado com ID: " + userDetails.getEventId()));
            existingUser.setEvents(event);
        } else {
            // Se o eventId for nulo, desassocia o usuário de qualquer evento.
            existingUser.setEvents(null);
        }

        // 4. Salva a entidade atualizada no banco.
        User updatedUser = userRepository.save(existingUser);

        // 5. Converte a entidade salva de volta para DTO e a retorna.
        return userMapper.toDTO(updatedUser);
    }

    /**
     * Deleta um usuário do banco de dados pelo seu ID.
     *
     * @param id O ID do usuário a ser deletado.
     * @throws ResourceNotFoundException se o usuário não for encontrado.
     */
    public void deleteUserById(Long id) {
        // 1. Verifica se o usuário existe antes de tentar deletar.
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com ID: " + id);
        }

        // 2. Deleta o usuário.
        userRepository.deleteById(id);
    }
}