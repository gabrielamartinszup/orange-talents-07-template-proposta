package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.VencimentoResponseDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento(){}

    public Vencimento(VencimentoResponseDto response) {
        this.apiId = response.getId();
        this.dia = response.getDia();
        this.dataDeCriacao = response.getDataDeCriacao();
    }
}
