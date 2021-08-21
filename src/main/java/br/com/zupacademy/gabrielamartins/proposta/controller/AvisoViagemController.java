package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.AvisoViagem;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.repository.AvisoViagemRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.CartaoRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.AvisoViagemRequestDto;
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


    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrarAvisoViagem(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequestDto request,
                                                  @RequestHeader(value = "User-Agent") String userAgent,
                                                  @RequestHeader(value = "ip") String ip){

        Optional<Cartao> cartaoObjetc = cartaoRepository.findById(id);
        if(cartaoObjetc.isPresent()){
            if (Objects.isNull(userAgent)) return ResponseEntity.unprocessableEntity().build();

            if (Objects.isNull(ip)) return ResponseEntity.unprocessableEntity().build();

            Cartao cartao = cartaoObjetc.get();
            AvisoViagem avisoViagem = request.converteParaAvisoViagem(userAgent, ip, cartao);
            avisoViagemRepository.save(avisoViagem);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
