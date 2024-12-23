package com.creditas.emprestimos.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class SimulacaoRequest {

    private BigDecimal valor;
    private LocalDate dataNascimento;
    private Integer parcelas;

}
