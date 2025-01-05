package com.ahad.location.server.repository;

import com.ahad.location.server.entity.ClientSpec;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public class ClientSpecRepository {

    private final DatabaseClient databaseClient;

    public ClientSpecRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    public Flux<ClientSpec> getAllClientSpecs() {
        return databaseClient.sql("SELECT * FROM client_spec")
                .map((row, metadata) -> new ClientSpec(row.get("name", String.class), row.get("pc", String.class), row.get("gt", String.class)))
                .all();
    }

    public Mono<Long> insertClientSpec(ClientSpec clientSpec) {
        return databaseClient.sql("INSERT INTO client_spec (name, pc, gt) VALUES (:name, :pc, :gt)")
                .bind("name", clientSpec.name())
                .bind("pc", clientSpec.pc())
                .bind("gt", clientSpec.gt())
                .fetch()
                .rowsUpdated();
    }

    public Mono<Long> updateClientSpec(ClientSpec clientSpec) {
        return databaseClient.sql("UPDATE client_spec SET  pc = :pc, gt = :gt WHERE name = :name")
                .bind("name", clientSpec.name())
                .bind("pc", clientSpec.pc())
                .bind("gt", clientSpec.gt())
                .fetch()
                .rowsUpdated();
    }

    public Mono<Long> deleteClientSpec(ClientSpec clientSpec) {
        return databaseClient.sql("DELETE FROM client_spec WHERE name = :name AND pc = :pc AND gt = :gt")
                .bind("name", clientSpec.name())
                .bind("pc", clientSpec.pc())
                .bind("gt", clientSpec.gt())
                .fetch()
                .rowsUpdated();
    }
}