package br.com.zupacademy.gabrielamartins.proposta.service;

import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.model.EstadoProposta;
import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.CartaoResponseDto;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class ServicoCartaoSchedule {


    private static final long delay = 1000L * 60L * 5L;

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    ServicoCartao servicoCartao;

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
}
