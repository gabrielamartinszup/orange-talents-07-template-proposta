package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.ParcelaResponseDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private Integer quantidade;
    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela(){}

    public Parcela(ParcelaResponseDto response) {
        this.apiId = response.getId();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return id.equals(parcela.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
