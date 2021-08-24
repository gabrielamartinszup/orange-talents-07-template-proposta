package br.com.zupacademy.gabrielamartins.proposta.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;

public class DocumentoLimpo {

    private String documento;

    @Valid
    public DocumentoLimpo(String documento) {
        this.documento = documento;
    }

    public String hash(){
        return new BCryptPasswordEncoder().encode(documento);
    }
}
