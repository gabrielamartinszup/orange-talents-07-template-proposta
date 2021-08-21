package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.AvisoResponse;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate terminaEm;
    private String destino;
    private LocalDateTime criadoEm;
    private String ip;
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(LocalDate terminaEm, String destino, String ip, String userAgent, Cartao cartao) {
        this.terminaEm = terminaEm;
        this.destino = destino;
        this.criadoEm = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
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
