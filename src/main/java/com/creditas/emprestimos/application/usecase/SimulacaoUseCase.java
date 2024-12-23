package com.creditas.emprestimos.application.usecase;


import com.creditas.emprestimos.domain.model.SimulacaoRequest;
import com.creditas.emprestimos.domain.model.SimulacaoResponse;

public interface SimulacaoUseCase {
    SimulacaoResponse execute(SimulacaoRequest simulation);
}
