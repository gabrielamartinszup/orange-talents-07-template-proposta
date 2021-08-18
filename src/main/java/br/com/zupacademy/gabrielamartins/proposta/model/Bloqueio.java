package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.BloqueioResponse;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){}

    public Bloqueio(BloqueioResponse response) {
        this.apiId = response.getId();
        this.bloqueadoEm = response.getBloqueadoEm();
        this.sistemaResponsavel = response.getSistemaResponsavel();
        this.ativo = response.isAtivo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return id.equals(bloqueio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
