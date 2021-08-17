package br.com.zupacademy.gabrielamartins.proposta.config.validation;

public class ErrorsOutputDto {

    private String campo;
    private String mensagem;

    public ErrorsOutputDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
