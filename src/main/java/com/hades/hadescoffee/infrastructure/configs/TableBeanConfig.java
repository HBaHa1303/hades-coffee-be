package com.hades.hadescoffee.infrastructure.configs;
import com.hades.hadescoffee.application.mappers.ATableMapper;
import com.hades.hadescoffee.application.usecases.table.*;
import com.hades.hadescoffee.domain.repositories.TablesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TableBeanConfig {

    private final TablesRepository repository;
    private final ATableMapper mapper;

    @Bean
    public CreateTableUseCase createTableUseCase() {
        return new CreateTableUseCase(repository, mapper);
    }

    @Bean
    public UpdateTableUseCase updateTableUseCase() {
        return new UpdateTableUseCase(repository);
    }

    @Bean
    public DeleteTableUseCase deleteTableUseCase() {
        return new DeleteTableUseCase(repository);
    }

    @Bean
    public GetTableByIdUseCase getTableByIdUseCase() {
        return new GetTableByIdUseCase(repository, mapper);
    }

    @Bean
    public GetTablesUseCase getTablesUseCase() {
        return new GetTablesUseCase(repository, mapper);
    }

    @Bean
    public ChangeTableStatusUseCase changeTableStatusUseCase() {
        return new ChangeTableStatusUseCase(repository);
    }
}