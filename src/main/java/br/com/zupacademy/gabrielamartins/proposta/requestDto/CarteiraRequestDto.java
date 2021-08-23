package br.com.zupacademy.gabrielamartins.proposta.requestDto;

import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.model.Carteira;
import br.com.zupacademy.gabrielamartins.proposta.model.GatewayPagamento;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequestDto {

    @NotBlank
    @Email
    private String email;
    @NotNull
    private GatewayPagamento carteira;

    @Deprecated
    public CarteiraRequestDto() {
    }

    public CarteiraRequestDto(String email, GatewayPagamento carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public GatewayPagamento getCarteira() {
        return carteira;
    }

    public Carteira converteParaCarteira(Cartao cartao){
        return new Carteira(email, carteira, cartao);
    }
}
