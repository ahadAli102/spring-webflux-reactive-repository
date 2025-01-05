package com.ahad.location.server.controller;

import com.ahad.location.server.entity.ClientSpec;
import com.ahad.location.server.response.GenericResponse;
import com.ahad.location.server.service.ClientSpecService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/client-specs")

public class ClientSpecController {
    private final ClientSpecService service;

    public ClientSpecController(ClientSpecService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<GenericResponse<List<ClientSpec>>> getAllClientSpecs() {
        return service.getAllClientSpecs();
    }

    @PostMapping
    public Mono<GenericResponse<Boolean>> insertClientSpec(@RequestBody ClientSpec clientSpec) {
        return service.insertClientSpec(clientSpec);
    }

    @PutMapping
    public Mono<GenericResponse<Boolean>> updateClientSpec(@RequestBody ClientSpec clientSpec) {
        return service.updateClientSpec(clientSpec);
    }

    @DeleteMapping
    public Mono<GenericResponse<Boolean>> deleteClientSpec(@RequestBody ClientSpec clientSpec) {
        return service.deleteClientSpec(clientSpec);
    }
}