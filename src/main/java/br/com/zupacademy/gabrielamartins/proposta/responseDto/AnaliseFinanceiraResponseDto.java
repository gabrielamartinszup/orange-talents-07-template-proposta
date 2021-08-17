package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import br.com.zupacademy.gabrielamartins.proposta.model.ResultadoSolicitacao;

public class AnaliseFinanceiraResponseDto {


    private String nome;

    private String documento;

    private Long idProposta;

    private ResultadoSolicitacao resultadoSolicitacao;

    public AnaliseFinanceiraResponseDto(String nome, String documento, Long idProposta, ResultadoSolicitacao resultadoSolicitacao) {
        this.nome = nome;
        this.documento = documento;
        this.idProposta = idProposta;
        this.resultadoSolicitacao = resultadoSolicitacao;
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

    public ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }
}
