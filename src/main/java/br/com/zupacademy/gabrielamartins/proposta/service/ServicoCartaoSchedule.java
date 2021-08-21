package br.com.zupacademy.gabrielamartins.proposta.service;

import br.com.zupacademy.gabrielamartins.proposta.model.*;
import br.com.zupacademy.gabrielamartins.proposta.repository.BloqueioRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.BloqueioRequest;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.BloqueioResponse;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
@EnableScheduling
public class ServicoCartaoSchedule {


    private static final long delay = 1000L * 30L * 1L;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ServicoCartao servicoCartao;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Scheduled(fixedDelay = delay)
    public void vincularCartaoProposta(){

        List<Proposta> propostas = propostaRepository.findAllByEstadoPropostaAndCartao(EstadoProposta.ELEGIVEL, null);
        propostas.forEach(proposta ->{
            try{
                CartaoResponseDto responseDto = servicoCartao.gerarCartao(proposta.getId().toString());
                proposta.associaCartao(new Cartao(responseDto));
                propostaRepository.save(proposta);
            } catch (FeignException e){
                e.getMessage();
            }
        });

    }

    public void bloquearCartao(Cartao cartao, String ip, String userAgent) {


            BloqueioResponse res = servicoCartao.bloquearCartao(cartao.getNumeroCartao(), new BloqueioRequest("propostas-webservice"));
            Bloqueio bloqueio = new Bloqueio(ip, userAgent, cartao);
            cartao.setStatusCartao(StatusCartao.BLOQUEADO);
            bloqueioRepository.save(bloqueio);


    }
}
