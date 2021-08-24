package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import br.com.zupacademy.gabrielamartins.proposta.model.ResultadoSolicitacao;
import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.AnaliseFinanceiraRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.PropostaRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.AnaliseFinanceiraResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.ParcelaResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.PropostaResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.service.AnaliseFinanceira;
import feign.FeignException;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private AnaliseFinanceira analiseFinanceira;


    private final Tracer tracer;

    public PropostaController(Tracer tracer) {
        this.tracer = tracer;
    }

    @PostMapping
    public ResponseEntity<?> criarProposta(@Valid @RequestBody PropostaRequestDto requestDto, UriComponentsBuilder componentsBuilder){
       Span activeSpan = tracer.activeSpan();
       activeSpan.setTag("user.email", "gabi@hotmail.com");
       activeSpan.log("Testando log");


        Proposta proposta = requestDto.converteParaProposta();

       if (proposta.existeProposta(propostaRepository)){
            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();
        }


        propostaRepository.save(proposta);
        resultadoSolicitacaoAnaliseFinanceira(proposta);


        URI uri = componentsBuilder
                .path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();



    }


    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponseDto> listarPorId(@PathVariable Long id){

        tracer.activeSpan().setBaggageItem("cartao.id", id.toString());

        Optional<Proposta> propostaObject = propostaRepository.findById(id);
        if(propostaObject.isPresent()){
            Proposta proposta = propostaObject.get();
            return ResponseEntity.ok(new PropostaResponseDto(proposta));
        }

        return ResponseEntity.notFound().build();

    }



    private void resultadoSolicitacaoAnaliseFinanceira(Proposta proposta) {
        AnaliseFinanceiraRequestDto requestDto = new AnaliseFinanceiraRequestDto(proposta);
        ResultadoSolicitacao resultadoSolicitacao;
        try{
            AnaliseFinanceiraResponseDto responseDto = analiseFinanceira.analisar(requestDto);
            resultadoSolicitacao = responseDto.getResultadoSolicitacao();
            proposta.setEstadoProposta(resultadoSolicitacao.elegibilidade());

        } catch (FeignException fe) {
            resultadoSolicitacao = ResultadoSolicitacao.COM_RESTRICAO;
            proposta.setEstadoProposta(resultadoSolicitacao.elegibilidade());
        }
    }
}
