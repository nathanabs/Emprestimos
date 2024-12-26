package com.creditas.emprestimos.application.usecase.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraEmprestimosUseCaseTest {

    private CalculadoraEmprestimosUseCase useCase;

    @BeforeEach
    void setUp(){
        useCase = new CalculadoraEmprestimosUseCase();
    }

    @Test
    void testCalculaPagamentoMensal() {

        BigDecimal valorEmprestimo = BigDecimal.valueOf(10000);
        BigDecimal taxaAnual = BigDecimal.valueOf(0.12);
        int parcelas = 12;

        BigDecimal pagamentoMensal = useCase.calculaPagamentoMensal(valorEmprestimo, taxaAnual, parcelas);

        BigDecimal valorEsperado = BigDecimal.valueOf(888.48);

        assertEquals(valorEsperado, pagamentoMensal);
    }

    @Test
    void testGetTaxaDeJuros() {
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(0.05), useCase.getTaxaDeJuros(20)),
                () -> assertEquals(BigDecimal.valueOf(0.03), useCase.getTaxaDeJuros(30)),
                () -> assertEquals(BigDecimal.valueOf(0.02), useCase.getTaxaDeJuros(50)),
                () -> assertEquals(BigDecimal.valueOf(0.04), useCase.getTaxaDeJuros(65))
        );
    }

}