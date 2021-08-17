package br.com.zupacademy.gabrielamartins.proposta.requestDto;

import br.com.zupacademy.gabrielamartins.proposta.config.validation.CPFouCNPJ;
import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PropostaRequestDto {


    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @CPFouCNPJ
    @NotBlank
    private String documento;
    @NotNull
    @Positive
    private Double salario;
    @NotBlank
    private String endereco;


    @Deprecated
    public PropostaRequestDto() {
    }

    public PropostaRequestDto(String nome, String email, String documento, Double salario, String endereco) {
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.salario = salario;
        this.endereco = endereco;
    }


    public Proposta converteParaProposta(){

        return new Proposta(nome, email, documento, salario, endereco);

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public Double getSalario() {
        return salario;
    }

    public String getEndereco() {
        return endereco;
    }
}
