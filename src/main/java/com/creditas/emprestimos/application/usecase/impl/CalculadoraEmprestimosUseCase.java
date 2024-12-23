package com.creditas.emprestimos.application.usecase.impl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculadoraEmprestimosUseCase {

    public BigDecimal calculaPagamentoMensal(BigDecimal valorEmprestimo, BigDecimal taxaAnual, int parcelas) {
        BigDecimal taxaDeJurosMensal = taxaAnual.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        // Calcula (1 + r)
        BigDecimal calculoTaxa = taxaDeJurosMensal.add(BigDecimal.ONE, mc);

        // Calcula (1 + r)^-n
        BigDecimal fatorPotencia = calculoTaxa.pow(-parcelas, mc);

        // Calcula 1 - (1 + r)^-n
        BigDecimal denominador = BigDecimal.ONE.subtract(fatorPotencia, mc);

        // Calcula PV * r
        BigDecimal numerador = valorEmprestimo.multiply(taxaDeJurosMensal, mc);

        // Calcula PMT = (PV * r) / (1 - (1 + r)^-n)
        BigDecimal pmt = numerador.divide(denominador, mc);

        return pmt.setScale(2, RoundingMode.DOWN);
    }

    public BigDecimal getTaxaDeJuros(int clientAge) {
        if (clientAge <= 25) return BigDecimal.valueOf(0.05);
        if (clientAge <= 40) return BigDecimal.valueOf(0.03);
        if (clientAge <= 60) return BigDecimal.valueOf(0.02);
        return BigDecimal.valueOf(0.04);
    }
}
