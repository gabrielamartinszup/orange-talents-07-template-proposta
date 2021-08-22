package br.com.zupacademy.gabrielamartins.proposta.service;

import br.com.zupacademy.gabrielamartins.proposta.requestDto.AvisoViagemRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.BloqueioRequest;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.AvisoViagemResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.BloqueioResponse;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "servicoCartao", url = "http://localhost:8888/api")
public interface ServicoCartao {

    @GetMapping("/cartoes")
    CartaoResponseDto gerarCartao(@RequestParam String idProposta);

    @PostMapping("/cartoes/{id}/bloqueios")
    BloqueioResponse bloquearCartao(@PathVariable String id, @RequestBody @Valid BloqueioRequest bloqueioRequest);

    @PostMapping("/cartoes/{id}/avisos")
    AvisoViagemResponseDto avisarSobreViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemRequestDto avisoViagemRequestDto);
}
