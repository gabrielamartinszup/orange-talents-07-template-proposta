package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.AvisoViagem;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.repository.AvisoViagemRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.CartaoRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.AvisoViagemRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.AvisoViagemResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.service.ServicoCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/avisos-viagens/cartoes")
public class AvisoViagemController {

    @Autowired
    AvisoViagemRepository avisoViagemRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    ServicoCartao servicoCartao;


    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrarAvisoViagem(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequestDto request,
                                                  @RequestHeader(value = "User-Agent") String userAgent,
                                                  @RequestHeader(value = "ip") String ip){

        Optional<Cartao> cartaoObjetc = cartaoRepository.findById(id);
        if(cartaoObjetc.isPresent()){
            if (Objects.isNull(userAgent)) return ResponseEntity.unprocessableEntity().build();

            if (Objects.isNull(ip)) return ResponseEntity.unprocessableEntity().build();

            Cartao cartao = cartaoObjetc.get();
            try {
                AvisoViagemResponseDto avisoViagemResponseDto = servicoCartao.avisarSobreViagem(cartao.getNumeroCartao(), request);
                if (resultadoAvisoViagem(avisoViagemResponseDto)) {
                    AvisoViagem avisoViagem = request.converteParaAvisoViagem(userAgent, ip, cartao);
                    avisoViagemRepository.save(avisoViagem);
                } else ResponseEntity.unprocessableEntity().body("Não foi possível realizar a notificação de viagem");
            }catch (FeignException e){
                return ResponseEntity.unprocessableEntity().body("Não foi possível realizar a notificação de viagem");
            }


            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    private boolean resultadoAvisoViagem(AvisoViagemResponseDto avisoViagemResponseDto){
        return avisoViagemResponseDto.getResultado().equals("CRIADO");
    }

}
