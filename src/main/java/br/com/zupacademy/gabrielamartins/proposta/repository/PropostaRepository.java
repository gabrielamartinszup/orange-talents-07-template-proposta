package br.com.zupacademy.gabrielamartins.proposta.repository;

import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
