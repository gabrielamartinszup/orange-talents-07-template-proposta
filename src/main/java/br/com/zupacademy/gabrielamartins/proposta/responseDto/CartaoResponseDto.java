package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class CartaoResponseDto {

    private String id;
    private String titular;
    private String idProposta;
    private BigDecimal limite;
    private LocalDateTime emitidoEm;
    private Set<AvisoViagemResponseDto> avisos;
    private Set<CarteiraResponseDto> carteiras;
    private Set<ParcelaResponseDto> parcelas;
    private RenegociacaoResponseDto renegociacao;
    private VencimentoResponseDto vencimento;

    public CartaoResponseDto(String id, String titular, String idProposta, BigDecimal limite, Set<AvisoViagemResponseDto> avisos,
                                     Set<CarteiraResponseDto> carteiras, Set<ParcelaResponseDto> parcelas, RenegociacaoResponseDto renegociacao, VencimentoResponseDto vencimento, LocalDateTime emitidoEm) {
        this.id = id;
        this.titular = titular;
        this.idProposta = idProposta;
        this.limite = limite;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }


    public Set<AvisoViagemResponseDto> getAvisos() {
        return avisos;
    }

    public Set<CarteiraResponseDto> getCarteiras() {
        return carteiras;
    }

    public Set<ParcelaResponseDto> getParcelas() {
        return parcelas;
    }

    public RenegociacaoResponseDto getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponseDto getVencimento() {
        return vencimento;
    }
}
