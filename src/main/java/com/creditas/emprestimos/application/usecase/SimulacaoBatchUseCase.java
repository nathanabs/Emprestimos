package com.creditas.emprestimos.application.usecase;


import com.creditas.emprestimos.domain.model.SimulacaoRequest;
import com.creditas.emprestimos.domain.model.SimulacaoResponse;

import java.util.List;

public interface SimulacaoBatchUseCase {
    List<SimulacaoResponse> execute(List<SimulacaoRequest> requests);
}
