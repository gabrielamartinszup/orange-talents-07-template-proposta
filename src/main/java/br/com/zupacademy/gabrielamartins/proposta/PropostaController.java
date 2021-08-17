package br.com.zupacademy.gabrielamartins.proposta;

import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.PropostaRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> criarProposta(@Valid @RequestBody PropostaRequestDto requestDto, UriComponentsBuilder componentsBuilder){

        Proposta proposta = requestDto.converteParaProposta();

       if (proposta.existeProposta(propostaRepository)){
            return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .build();
        }

        propostaRepository.save(proposta);

        URI uri = componentsBuilder
                .path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();



    }
}
