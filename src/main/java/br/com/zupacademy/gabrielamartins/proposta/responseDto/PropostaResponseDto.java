package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import br.com.zupacademy.gabrielamartins.proposta.model.EstadoProposta;
import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;

public class PropostaResponseDto {

    private Long id;
    private String nome;
    private String email;
    private String documento;
    private Double salario;
    private String endereco;
    private EstadoProposta estadoProposta;

    public PropostaResponseDto(Proposta proposta) {
        this.id = proposta.getId();
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.documento = proposta.getDocumento();
        this.salario = proposta.getSalario();
        this.endereco = proposta.getEndereco();
        this.estadoProposta = proposta.getEstadoProposta();
    }

    public Long getId() {
        return id;
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

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }
}
