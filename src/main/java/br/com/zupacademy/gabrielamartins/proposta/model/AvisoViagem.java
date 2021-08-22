package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.requestDto.AvisoViagemRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.AvisoViagemResponseDto;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate validoAte;
    private String destino;
    private LocalDateTime criadoEm = LocalDateTime.now();
    private String ip;
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(LocalDate validoAte, String destino, String ip, String userAgent, Cartao cartao) {
        this.validoAte = validoAte;
        this.destino = destino;
        this.criadoEm = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public AvisoViagem(AvisoViagemRequestDto requestDto, String ip, String userAgent, Cartao cartao){
        this.validoAte = requestDto.getValidoAte();
        this.cartao = cartao;
        this.destino = requestDto.getDestino();
        this.ip = ip;
        this.userAgent = userAgent;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getIp() {
        return ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem avisoViagem = (AvisoViagem) o;
        return id.equals(avisoViagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
