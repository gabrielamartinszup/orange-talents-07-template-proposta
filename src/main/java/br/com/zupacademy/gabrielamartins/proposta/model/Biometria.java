package br.com.zupacademy.gabrielamartins.proposta.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String fingerprint;
    @ManyToOne
    private Cartao cartao;
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Deprecated
    public Biometria() {
    }

    public Biometria(EncodedFinger encodedFinger, Cartao cartao){
        this.fingerprint = encodedFinger.getFingerprint();
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}
