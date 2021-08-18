package br.com.zupacademy.gabrielamartins.proposta.service;

import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "servicoCartao", url = "http://localhost:8888/api")
public interface ServicoCartao {

    @GetMapping("/cartoes")
    CartaoResponseDto gerarCartao(@RequestParam String idProposta);
}
