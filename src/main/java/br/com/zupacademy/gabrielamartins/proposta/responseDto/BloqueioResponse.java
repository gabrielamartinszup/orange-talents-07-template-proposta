package br.com.zupacademy.gabrielamartins.proposta.responseDto;

import br.com.zupacademy.gabrielamartins.proposta.model.Bloqueio;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class BloqueioResponse {

    private String resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioResponse(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }

    public Bloqueio converteParaBloqueio(HttpServletRequest request, Cartao cartao){
        String ip = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        return new Bloqueio(ip, userAgent, cartao);
    }
}
