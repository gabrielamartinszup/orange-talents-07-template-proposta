package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.RenegociacaoResponseDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Renegociacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Renegociacao(){}

    public Renegociacao(RenegociacaoResponseDto response) {
        this.apiId = response.getId();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
        this.dataDeCriacao = response.getDataDeCriacao();
    }
}
