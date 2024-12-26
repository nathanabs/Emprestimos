package com.creditas.emprestimos.application.usecase.impl;

import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimulacaoEmprestimosBatchUseCaseTest {

    @Mock
    private SimulacaoUseCase simulacaoUseCase;

    private SimulacaoEmprestimosBatchUseCase simulacaoEmprestimosBatchUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        simulacaoEmprestimosBatchUseCase = new SimulacaoEmprestimosBatchUseCase(simulacaoUseCase);
    }

    @Test
    void testExecuteComSucesso() throws Exception {
        // Mock das entradas
        EmprestimoRequest request1 = EmprestimoRequest.builder()
                .dataNascimento(LocalDate.of(1994, 4, 6)).build();

        // Lista de requests
        List<EmprestimoRequest> requests = Arrays.asList(request1);

        // Executa o método
        List<EmprestimoResponse> responses = simulacaoEmprestimosBatchUseCase.execute(requests);

        assertAll(
                () -> assertEquals(1, responses.size()),
                () -> verify(simulacaoUseCase, times(1)).execute(request1)
        );

    }

}