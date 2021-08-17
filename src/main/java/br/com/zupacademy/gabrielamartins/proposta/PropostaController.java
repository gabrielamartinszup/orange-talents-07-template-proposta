package br.com.zupacademy.gabrielamartins.proposta;

import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import br.com.zupacademy.gabrielamartins.proposta.repository.PropostaRepository;
import br.com.zupacademy.gabrielamartins.proposta.requestDto.PropostaRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    public ResponseEntity<?> criarProposta(@Valid @RequestBody PropostaRequestDto requestDto, UriComponentsBuilder componentsBuilder){

        Proposta proposta = requestDto.converteParaProposta();
        propostaRepository.save(proposta);

        URI uri = componentsBuilder
                .path("/propostas/{id}")
                .buildAndExpand(proposta.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }
}
