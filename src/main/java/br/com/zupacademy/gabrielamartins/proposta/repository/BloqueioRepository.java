package br.com.zupacademy.gabrielamartins.proposta.repository;

import br.com.zupacademy.gabrielamartins.proposta.model.Bloqueio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<Bloqueio, Long> {
    Optional<Bloqueio> findByCartaoId(Long id);
}
