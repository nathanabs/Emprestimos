package com.creditas.emprestimos.infraestructure.controller;

import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import com.creditas.emprestimos.domain.model.SimulacaoRequest;
import com.creditas.emprestimos.domain.model.SimulacaoResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimosController {

    private final SimulacaoUseCase useCase;

    public EmprestimosController(SimulacaoUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public SimulacaoResponse getEmprestimos(
            @RequestParam BigDecimal valorEmprestimo,
            @RequestParam LocalDate dataNascimento,
            @RequestParam Integer quantidadeParcelas){
        var simulacao = SimulacaoRequest.builder()
                .valor(valorEmprestimo)
                .dataNascimento(dataNascimento)
                .parcelas(quantidadeParcelas)
                .build();
        return useCase.execute(simulacao);
    }
}
