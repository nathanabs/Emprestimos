package com.creditas.emprestimos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class SimulacaoResponse {

    private BigDecimal valorTotal;
    private BigDecimal valorParcelas;
    private BigDecimal totalJuros;


}
