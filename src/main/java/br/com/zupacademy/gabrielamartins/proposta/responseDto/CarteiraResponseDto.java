package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.time.LocalDateTime;

public class CarteiraResponseDto {

    private String resultado;
    private String id;


    @Deprecated
    public CarteiraResponseDto(){}


    public CarteiraResponseDto(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
