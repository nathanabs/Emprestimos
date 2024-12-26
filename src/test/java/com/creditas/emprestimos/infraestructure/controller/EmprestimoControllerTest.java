package com.creditas.emprestimos.infraestructure.controller;

import com.creditas.emprestimos.application.usecase.SimulacaoBatchUseCase;
import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmprestimoControllerTest {

    @Mock
    private SimulacaoUseCase useCase;

    @Mock
    private SimulacaoBatchUseCase batchUseCase;

    @InjectMocks
    private EmprestimoController controller;

    @Test
    public void testGetSimulacao() {
        // Dados de entrada
        var valor = BigDecimal.valueOf(10000);
        var dataNascimento = LocalDate.of(1990, 1, 1);
        var parcelas = 12;

        var expectedResponse = new EmprestimoResponse(BigDecimal.valueOf(10272.84), BigDecimal.valueOf(856.07), BigDecimal.valueOf(272.84));
        when(useCase.execute(new EmprestimoRequest(valor, dataNascimento, parcelas))).thenReturn(expectedResponse);

        var response = controller.getSimulacao(valor, dataNascimento, parcelas);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expectedResponse, response.getBody())
        );

    }

    @Test
    public void testRealizarSimulacoes() {
        var requests = new ArrayList<EmprestimoRequest>();
        requests.add(new EmprestimoRequest(BigDecimal.valueOf(10000), LocalDate.of(1990, 1, 1), 12));
        requests.add(new EmprestimoRequest(BigDecimal.valueOf(5000), LocalDate.of(1985, 5, 15), 6));

        var expectedResponses = new ArrayList<EmprestimoResponse>();
        expectedResponses.add(new EmprestimoResponse(BigDecimal.valueOf(10272.84), BigDecimal.valueOf(856.07), BigDecimal.valueOf(272.84)));
        expectedResponses.add(new EmprestimoResponse(BigDecimal.valueOf(5123.45), BigDecimal.valueOf(853.91), BigDecimal.valueOf(123.45)));
        when(batchUseCase.execute(requests)).thenReturn(expectedResponses);

        var response = controller.realizarSimulacoes(requests);

        assertAll(
                () ->  assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(expectedResponses, response.getBody())
        );
    }

}