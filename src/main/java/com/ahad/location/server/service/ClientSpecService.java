package com.ahad.location.server.service;

import com.ahad.location.server.entity.ClientSpec;
import com.ahad.location.server.repository.ClientSpecRepository;
import com.ahad.location.server.response.GenericResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClientSpecService {
    private final ClientSpecRepository repository;

    public ClientSpecService(ClientSpecRepository repository) {
        this.repository = repository;
    }

    public Mono<GenericResponse<List<ClientSpec>>> getAllClientSpecs() {
        return repository.getAllClientSpecs()
                .collectList()
                .map(clientSpec -> new GenericResponse<>("D200", "Success", clientSpec))
                .onErrorResume(e -> Mono.just(new GenericResponse<>("D400", e.getMessage(), List.of())));
    }

    public Mono<GenericResponse<Boolean>> insertClientSpec(ClientSpec clientSpec) {
        return repository.insertClientSpec(clientSpec)
                .map(rowsUpdated -> new GenericResponse<>("D200", "Success", rowsUpdated > 0))
                .onErrorResume(e -> Mono.just(new GenericResponse<>("D400", e.getMessage(), false)));
    }

    public Mono<GenericResponse<Boolean>> updateClientSpec(ClientSpec clientSpec) {
        return repository.updateClientSpec(clientSpec)
                .map(rowsUpdated -> new GenericResponse<>("D200", "Success", rowsUpdated > 0))
                .onErrorResume(e -> Mono.just(new GenericResponse<>("D400", e.getMessage(), false)));
    }

    public Mono<GenericResponse<Boolean>> deleteClientSpec(ClientSpec clientSpec) {
        return repository.deleteClientSpec(clientSpec)
                .map(rowsUpdated -> new GenericResponse<>("D200", "Success", rowsUpdated > 0))
                .onErrorResume(e -> Mono.just(new GenericResponse<>("D400", e.getMessage(), false)));
    }
}