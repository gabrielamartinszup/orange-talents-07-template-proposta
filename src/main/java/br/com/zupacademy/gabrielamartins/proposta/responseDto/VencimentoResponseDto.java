package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.time.LocalDateTime;

public class VencimentoResponseDto {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public VencimentoResponseDto(){}

    public VencimentoResponseDto(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
