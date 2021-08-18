package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.CarteiraResponseDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira(){}

    public Carteira(CarteiraResponseDto response) {
        this.apiId = response.getId();
        this.email = response.getEmail();
        this.associadoEm = response.getAssociadoEm();
        this.emissor = response.getEmissor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carteira carteira = (Carteira) o;
        return id.equals(carteira.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
