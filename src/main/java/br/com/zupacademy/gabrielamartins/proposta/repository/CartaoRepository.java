package br.com.zupacademy.gabrielamartins.proposta.repository;

import br.com.zupacademy.gabrielamartins.proposta.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartaoRepository extends JpaRepository<Cartao, Long> {


}
