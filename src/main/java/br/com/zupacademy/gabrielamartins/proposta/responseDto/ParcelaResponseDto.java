package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.math.BigDecimal;

public class ParcelaResponseDto {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @Deprecated
    public ParcelaResponseDto(){}

    public ParcelaResponseDto(ParcelaResponseDto response) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
