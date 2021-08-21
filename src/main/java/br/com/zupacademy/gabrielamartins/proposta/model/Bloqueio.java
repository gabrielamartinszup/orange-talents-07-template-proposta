package br.com.zupacademy.gabrielamartins.proposta.model;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime bloqueadoEm;
    private String ip;
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){}


    public Bloqueio(String ip, String userAgent, Cartao cartao) {
        this.bloqueadoEm = LocalDateTime.now();
        this.ip = ip;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }


}
