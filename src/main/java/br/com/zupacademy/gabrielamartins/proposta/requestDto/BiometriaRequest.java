package br.com.zupacademy.gabrielamartins.proposta.requestDto;

import br.com.zupacademy.gabrielamartins.proposta.model.Biometria;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.model.EncodedFinger;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    private String fingerprint;

    @Deprecated
    public BiometriaRequest() {
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BiometriaRequest(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Biometria converteParaBiometria(Cartao cartao){
        return new Biometria(new EncodedFinger(fingerprint), cartao);
    }
}
