package br.com.zupacademy.gabrielamartins.proposta.service;

import br.com.zupacademy.gabrielamartins.proposta.requestDto.AnaliseFinanceiraRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.AnaliseFinanceiraResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name= "analiseFinanceiraService", url="http://localhost:9999/api")
public interface AnaliseFinanceira {

    @PostMapping("/solicitacao")
    AnaliseFinanceiraResponseDto analisar(@RequestBody @Valid AnaliseFinanceiraRequestDto analiseFinanceiraRequestDto);
}
