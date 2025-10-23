package com.mendes.api_system_registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * Classe Centralizadora para Tratamento de Exceções.
 *
 * O que esta classe faz?
 * Ela atua como um "guardião" ou "conselheiro" (Advice) para toda a aplicação.
 * Em vez de tratar erros (com try-catch) dentro de cada método do Controller,
 * nós deixamos as exceções "estourarem". Esta classe irá capturá-las
 * e traduzi-las em uma resposta JSON bonita e padronizada para o cliente (frontend/Postman).
 *
 * Como funciona?
 * A anotação @ControllerAdvice informa ao Spring: "Observe todos os meus @Controllers.
 * Se algum deles lançar uma exceção que você sabe como tratar (veja @ExceptionHandler),
 * não deixe o servidor quebrar. Envie a exceção para cá primeiro."
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manipulador (Handler) para a exceção 'ResourceNotFoundException'.
     *
     * Quando este método é acionado?
     * Sempre que qualquer parte do nosso código (geralmente os Services)
     * executar o comando: throw new ResourceNotFoundException("Usuário não encontrado...");
     *
     * O que ele faz?
     * 1. O Spring captura a exceção 'ResourceNotFoundException'.
     * 2. Ele procura um método anotado com @ExceptionHandler(ResourceNotFoundException.class)
     * dentro de qualquer classe @ControllerAdvice. Ele encontra este aqui.
     * 3. Ele executa o método, passando a exceção (ex) como parâmetro.
     * 4. Nós criamos nosso objeto 'ErrorResponse' padronizado, definindo o status
     * para 404 (NOT_FOUND) e pegando a mensagem da exceção (ex.getMessage()).
     * 5. Retornamos um ResponseEntity com o status 404 e o 'ErrorResponse' no corpo.
     *
     * @param ex A exceção ResourceNotFoundException que foi capturada.
     * @return Um ResponseEntity padronizado para o cliente com status 404.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        // Cria o corpo do erro usando nossa classe padrão
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        // Retorna a resposta HTTP formatada
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    /**
     * Manipulador (Handler) para exceções de Validação de Argumentos.
     *
     * Quando este método é acionado?
     * Sempre que um endpoint do Controller que tenha um parâmetro anotado com @Valid
     * (ex: @Valid @RequestBody UserDTO userDTO) falha na validação.
     * Isso acontece se o JSON enviado viola as regras que definimos no DTO
     * (ex: @NotBlank, @Email, @Size).
     *
     * O que ele faz?
     * 1. O Spring captura a exceção 'MethodArgumentNotValidException'.
     * 2. Ele encontra este método, que está esperando por essa exceção exata.
     * 3. A exceção (ex) contém uma lista de *todos* os campos que falharam na validação.
     * 4. Usamos 'ex.getBindingResult().getFieldErrors()' para obter essa lista.
     * 5. Com 'stream().map().collect()', nós formatamos a lista de erros em uma
     * única String legível (ex: "cpf: O CPF não pode estar em branco | email: O formato do email é inválido").
     * 6. Criamos nosso 'ErrorResponse' com status 400 (BAD_REQUEST) e a mensagem de erro formatada.
     * 7. Retornamos o ResponseEntity padronizado para o cliente.
     *
     * @param ex A exceção MethodArgumentNotValidException que foi capturada.
     * @return Um ResponseEntity padronizado para o cliente com status 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        // 'ex.getBindingResult()' contém os resultados da validação
        // 'getFieldErrors()' pega todos os campos que falharam
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                // Para cada erro de campo, formata a string (ex: "name: não pode ser nulo")
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                // Junta todas as strings de erro, separadas por " | "
                .collect(Collectors.joining(" | "));

        // Cria o corpo do erro com a mensagem concatenada
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);

        // Retorna a resposta HTTP formatada
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}