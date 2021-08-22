package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import java.time.LocalDate;

public class AvisoViagemResponseDto {

    private String resultado;


    @Deprecated
    public AvisoViagemResponseDto() {
    }

    public AvisoViagemResponseDto(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
