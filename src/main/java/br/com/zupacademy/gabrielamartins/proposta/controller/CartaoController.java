package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.Biometria;
import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.repository.BiometriaRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.CartaoRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.BiometriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

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
}
