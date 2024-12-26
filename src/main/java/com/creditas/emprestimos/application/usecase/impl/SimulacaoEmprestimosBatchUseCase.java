package com.creditas.emprestimos.application.usecase.impl;

import com.creditas.emprestimos.application.usecase.SimulacaoBatchUseCase;
import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import com.creditas.emprestimos.domain.model.SimulacaoRequest;
import com.creditas.emprestimos.domain.model.SimulacaoResponse;
import org.openapitools.model.EmprestimoRequest;
import org.openapitools.model.EmprestimoResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class SimulacaoEmprestimosBatchUseCase implements SimulacaoBatchUseCase {

    private final SimulacaoUseCase simulacaoUseCase;
    private final ExecutorService executor;
    private final Semaphore semaphore;


    public SimulacaoEmprestimosBatchUseCase(SimulacaoUseCase simulacaoUseCase) {
        this.simulacaoUseCase = simulacaoUseCase;
        this.executor = Executors.newVirtualThreadPerTaskExecutor();
        this.semaphore = new Semaphore(100);
    }


    @Override
    public List<EmprestimoResponse> execute(List<EmprestimoRequest> requests) {
        List<Future<EmprestimoResponse>> futuros = new ArrayList<>();
        requests.forEach(objeto -> {
            try {
                semaphore.acquire();
                Future<EmprestimoResponse> futuro = executor.submit(() -> {
                    try {
                        return simulacaoUseCase.execute(objeto);
                    } finally {
                        semaphore.release();
                    }
                });
                futuros.add(futuro);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrompida: " + e.getMessage());
            }
        });
        var results =  coletarResultados(futuros);
        return results;
    }

    private List<EmprestimoResponse> coletarResultados(List<Future<EmprestimoResponse>> futuros) {
        List<EmprestimoResponse> resultados = new ArrayList<>();
        for (Future<EmprestimoResponse> futuro : futuros) {
            try {
                resultados.add(futuro.get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrompida ao coletar resultados: " + e.getMessage());
            } catch (ExecutionException e) {
                System.err.println("Erro na execução da tarefa: " + e.getMessage());
            }
        }
        return resultados;
    }
}
