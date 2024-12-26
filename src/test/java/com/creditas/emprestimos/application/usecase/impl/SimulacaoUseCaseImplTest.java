package com.creditas.emprestimos.application.usecase.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.EmprestimoRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimulacaoUseCaseImplTest {

    @Mock
    private CalculadoraEmprestimosUseCase calculadora;

    @InjectMocks
    private SimulacaoUseCaseImpl simulacaoUseCase;

    @Test
    void testExecute() {
        var simulacao = new EmprestimoRequest(BigDecimal.valueOf(10000), LocalDate.of(1990, 1, 1), 12);
        when(calculadora.getTaxaDeJuros(34)).thenReturn(BigDecimal.valueOf(0.05));
        when(calculadora.calculaPagamentoMensal(BigDecimal.valueOf(10000), BigDecimal.valueOf(0.05), 12))
                .thenReturn(BigDecimal.valueOf(856.07));

        var response = simulacaoUseCase.execute(simulacao);

        assertAll(
                () -> assertEquals(BigDecimal.valueOf(10272.84), response.getValorTotal()),
                () -> assertEquals(BigDecimal.valueOf(856.07), response.getValorParcelas()),
                () -> assertEquals(BigDecimal.valueOf(272.84), response.getTotalJuros())
        );
    }

}