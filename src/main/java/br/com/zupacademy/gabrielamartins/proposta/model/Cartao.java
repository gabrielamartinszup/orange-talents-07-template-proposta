package br.com.zupacademy.gabrielamartins.proposta.model;


import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "cartao")
    private Set<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AvisoViagem> avisosViagem;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Carteira> carteiras;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Parcela> parcelas;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Renegociacao renegociacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    private String idProposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(CartaoResponseDto response) {
        this.statusCartao = StatusCartao.ATIVO;
        this.numeroCartao = response.getId();
        this.nomeTitular = response.getTitular();
        this.limite = response.getLimite();
        this.emitidoEm = response.getEmitidoEm();



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


    public Long getId() {
        return id;
    }

    public void setStatusCartao(StatusCartao statusCartao) {
        this.statusCartao = statusCartao;
    }


    public boolean isCartaoBloqueado() {
        return this.statusCartao.equals(StatusCartao.BLOQUEADO);
    }
}
