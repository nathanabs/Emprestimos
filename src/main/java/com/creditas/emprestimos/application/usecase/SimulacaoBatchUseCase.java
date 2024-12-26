package com.creditas.emprestimos.application.usecase;


import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;

import java.util.List;

public interface SimulacaoBatchUseCase {
    List<EmprestimoResponse> execute(List<EmprestimoRequest> requests);
}
