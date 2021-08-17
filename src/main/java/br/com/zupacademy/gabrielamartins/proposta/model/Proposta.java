package br.com.zupacademy.gabrielamartins.proposta.model;

import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String documento;
    @NotNull
    @Positive
    private Double salario;
    @NotBlank
    private String endereco;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String nome, String email, String documento, Double salario, String endereco) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.salario = salario;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public boolean existeProposta(PropostaRepository propostaRepository) {
        return propostaRepository.findByDocumento(this.documento).isPresent();
    }
}
