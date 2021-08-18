package br.com.zupacademy.gabrielamartins.proposta.model;


import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    private String nomeTitular;
    private BigDecimal limite;
    private LocalDateTime emitidoEm;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Aviso> avisos;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Carteira> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Parcela> parcelas;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Renegociacao renegociacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    private String idProposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(CartaoResponseDto response) {
        this.numeroCartao = response.getId();
        this.nomeTitular = response.getTitular();
        this.limite = response.getLimite();
        this.emitidoEm = response.getEmitidoEm();

        if (!response.getBloqueios().isEmpty()) {
            this.bloqueios = response.getBloqueios().stream().map(Bloqueio::new).collect(Collectors.toSet());
        }

        if (!response.getAvisos().isEmpty()) {
            this.avisos = response.getAvisos().stream().map(Aviso::new).collect(Collectors.toSet());
        }

        if (!response.getCarteiras().isEmpty()) {
            this.carteiras = response.getCarteiras().stream().map(Carteira::new).collect(Collectors.toSet());
        }

        if (!response.getParcelas().isEmpty()) {
            this.parcelas = response.getParcelas().stream().map(Parcela::new).collect(Collectors.toSet());
        }

        if (!Objects.isNull(response.getRenegociacao())) {
            this.renegociacao = new Renegociacao(response.getRenegociacao());
        }

        if (!Objects.isNull(response.getVencimento())) {
            this.vencimento = new Vencimento(response.getVencimento());
        }

        this.idProposta = response.getIdProposta();
    }

    public String getNumeroCartao() {
        return this.numeroCartao;
    }

}
