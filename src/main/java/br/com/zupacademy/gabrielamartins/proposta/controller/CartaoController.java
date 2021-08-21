package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.Biometria;
import br.com.zupacademy.gabrielamartins.proposta.model.Bloqueio;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.model.StatusCartao;
import br.com.zupacademy.gabrielamartins.proposta.repository.BiometriaRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.BloqueioRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.CartaoRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.BiometriaRequest;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.BloqueioRequest;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.BloqueioResponse;
import br.com.zupacademy.gabrielamartins.proposta.service.ServicoCartao;
import br.com.zupacademy.gabrielamartins.proposta.service.ServicoCartaoSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private ServicoCartao servicoCartao;

    @Autowired
    private ServicoCartaoSchedule servicoCartaoSchedule;


    @PostMapping("/{id}/biometria")
    public ResponseEntity<?> cadastrarBiometria(@PathVariable Long id, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder builder){

        Optional<Cartao> cartaoObject = cartaoRepository.findById(id);
        if(cartaoObject.isPresent()){
            Cartao cartao = cartaoObject.get();
            Biometria biometria = biometriaRequest.converteParaBiometria(cartao);
            biometriaRepository.save(biometria);

            URI uri = builder.path("/cartoes/{id}/biometria/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();

           return ResponseEntity.created(uri).build();

        }

        return ResponseEntity.notFound().build();
    }




    @PostMapping("/{id}/bloqueio")
    public ResponseEntity<?> bloquearCartao(@PathVariable Long id, @RequestHeader(value = "User-Agent") String userAgent,
                                               @RequestHeader(value = "ip") String ip) {

        Optional<Cartao> cartaoObject = cartaoRepository.findById(id);
        if (cartaoObject.isPresent()) {
            if (Objects.isNull(userAgent)) return ResponseEntity.badRequest().build();
            if (Objects.isNull(ip)) return ResponseEntity.badRequest().build();
            Optional<Bloqueio> possivelBloqueio = bloqueioRepository.findByCartaoId(id);
            if(possivelBloqueio.isPresent()) return ResponseEntity.unprocessableEntity().build();

            Cartao cartao = cartaoObject.get();
            servicoCartaoSchedule.bloquearCartao(cartao, ip, userAgent);

            if (!cartao.isCartaoBloqueado()) return ResponseEntity.badRequest().build();

            return ResponseEntity.ok().build();
        }


        return ResponseEntity.notFound().build();
    }
}
