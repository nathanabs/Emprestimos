package com.creditas.emprestimos.infraestructure.controller;

import com.creditas.emprestimos.application.usecase.SimulacaoBatchUseCase;
import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import org.openapitools.api.EmprestimosApi;
import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class EmprestimoController implements EmprestimosApi {

    private final SimulacaoUseCase useCase;
    private final SimulacaoBatchUseCase batchUseCase;

    public EmprestimoController(SimulacaoUseCase useCase, SimulacaoBatchUseCase batchUseCase) {
        this.useCase = useCase;
        this.batchUseCase = batchUseCase;
    }

    @Override
    public ResponseEntity<EmprestimoResponse> getSimulacao(BigDecimal valor, LocalDate dataNascimento, Integer parcelas) {
        var emprestimo = EmprestimoRequest.builder().valor(valor).dataNascimento(dataNascimento).parcelas(parcelas).build();
        return ResponseEntity.ok(useCase.execute(emprestimo));
    }

    @Override
    public ResponseEntity<List<EmprestimoResponse>> realizarSimulacoes(List<EmprestimoRequest> emprestimoRequest) {
        return ResponseEntity.ok(batchUseCase.execute(emprestimoRequest));
    }
}
