package com.creditas.emprestimos.application.usecase.impl;


import com.creditas.emprestimos.application.usecase.SimulacaoUseCase;
import com.creditas.emprestimos.domain.model.SimulacaoRequest;
import com.creditas.emprestimos.domain.model.SimulacaoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

@Service
public class SimulacaoUseCaseImpl implements SimulacaoUseCase {

    private final CalculadoraEmprestimosUseCase calculadora;

    public SimulacaoUseCaseImpl(CalculadoraEmprestimosUseCase calculadora) {
        this.calculadora = calculadora;
    }

    @Override
    public SimulacaoResponse execute(SimulacaoRequest simulacao) {
        int idade = getIdade(simulacao.getDataNascimento());
        var taxaDeJuros = calculadora.getTaxaDeJuros(idade);
        var monthlyPayment = calculadora.calculaPagamentoMensal(
                simulacao.getValor(), taxaDeJuros, simulacao.getParcelas()
        );

        var totalPagamento = monthlyPayment.multiply(BigDecimal.valueOf(simulacao.getParcelas())).setScale(2, RoundingMode.DOWN);
        var jurosTotal = totalPagamento.subtract(simulacao.getValor()).setScale(2, RoundingMode.DOWN);

        return new SimulacaoResponse(totalPagamento, monthlyPayment, jurosTotal);
    }

    private int getIdade(LocalDate date) {
        return Period.between(date, LocalDate.now()).getYears();
    }
}
