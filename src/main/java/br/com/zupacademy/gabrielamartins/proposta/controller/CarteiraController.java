package br.com.zupacademy.gabrielamartins.proposta.controller;

import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import br.com.zupacademy.gabrielamartins.proposta.model.Carteira;
import br.com.zupacademy.gabrielamartins.proposta.repository.CartaoRepository;
import br.com.zupacademy.gabrielamartins.proposta.repository.CarteiraRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.CarteiraRequestDto;
import br.com.zupacademy.gabrielamartins.proposta.responseDto.CarteiraResponseDto;
import br.com.zupacademy.gabrielamartins.proposta.service.ServicoCartao;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ServicoCartao servicoCartao;

    @PostMapping("/cartoes/{id}")
    public ResponseEntity<?> cadastrarCarteira(@PathVariable Long id, @RequestBody @Valid CarteiraRequestDto carteiraRequestDto, UriComponentsBuilder builder){
        Optional<Cartao> cartaoObject = cartaoRepository.findById(id);
        if (cartaoObject.isEmpty()) return ResponseEntity.notFound().build();
        Cartao cartao = cartaoObject.get();

        Optional<Carteira> carteiraObject = carteiraRepository.findByCarteiraAndCartaoId(carteiraRequestDto.getCarteira(), cartao.getId());
        if(carteiraObject.isPresent()) return ResponseEntity.unprocessableEntity().body("Cartão já está associado a esta carteira");

        try {
            CarteiraResponseDto carteiraResponseDto = servicoCartao.associarCarteira(cartao.getNumeroCartao(), carteiraRequestDto);

            if (carteiraResponseDto.getResultado().equals("ASSOCIADA")){
                Carteira carteira = carteiraRequestDto.converteParaCarteira(cartao);
                carteiraRepository.save(carteira);
                URI uri = builder.path("/carteiras/cartoes/{id}").buildAndExpand(cartao.getNumeroCartao()).toUri();
                return ResponseEntity.created(uri).build();

            } else {

                return ResponseEntity.unprocessableEntity().build();
            }


        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.badRequest().build();
        }



    }
}
