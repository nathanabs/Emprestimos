package com.creditas.emprestimos.application.usecase;


import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;

public interface SimulacaoUseCase {
    EmprestimoResponse execute(EmprestimoRequest simulation);
}
