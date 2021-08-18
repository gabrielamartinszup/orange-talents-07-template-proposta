package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponseDto {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public RenegociacaoResponseDto(){}

    public RenegociacaoResponseDto(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
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

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
