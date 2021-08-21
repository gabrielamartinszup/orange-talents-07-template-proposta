package br.com.zupacademy.gabrielamartins.proposta.requestDto;

import br.com.zupacademy.gabrielamartins.proposta.model.AvisoViagem;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemRequestDto {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate terminaEm;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemRequestDto(String destino, @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING) LocalDate terminaEm) {
        this.destino = destino;
        this.terminaEm = terminaEm;
    }

    public AvisoViagem converteParaAvisoViagem(String ip, String userAgent, Cartao cartao){
        return new AvisoViagem(terminaEm, destino, ip, userAgent, cartao);
    }
}
