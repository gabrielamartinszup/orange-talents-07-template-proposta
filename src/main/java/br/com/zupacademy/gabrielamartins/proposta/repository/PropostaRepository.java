package br.com.zupacademy.gabrielamartins.proposta.repository;

import br.com.zupacademy.gabrielamartins.proposta.model.EstadoProposta;
import br.com.zupacademy.gabrielamartins.proposta.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);

    List<Proposta> findAllByEstadoPropostaAndCartao(EstadoProposta estadoProposta, Long cartaoId);
}
