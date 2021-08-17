package br.com.zupacademy.gabrielamartins.proposta.requestDto;

import br.com.zupacademy.gabrielamartins.proposta.config.validation.CPFouCNPJ;
import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnaliseFinanceiraRequestDto {

    @NotBlank
    private String nome;
    @NotBlank
    @CPFouCNPJ
    private String documento;
    @NotNull
    private Long idProposta;

    public AnaliseFinanceiraRequestDto(String nome, String documento, Long idProposta) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
    }

    public AnaliseFinanceiraRequestDto(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
