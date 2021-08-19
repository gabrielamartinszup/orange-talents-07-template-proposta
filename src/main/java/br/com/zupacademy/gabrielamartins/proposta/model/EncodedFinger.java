package br.com.zupacademy.gabrielamartins.proposta.model;


import org.apache.tomcat.util.codec.binary.Base64;

public class EncodedFinger {

    private String fingerprint;

    public EncodedFinger(String senhaEncoded) {
        if(!Base64.isBase64(senhaEncoded))
            throw new IllegalArgumentException("Fingerprint não é um Base64 válido!");

        this.fingerprint = senhaEncoded;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
