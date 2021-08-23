package br.com.zupacademy.gabrielamartins.proposta.repository;

import br.com.zupacademy.gabrielamartins.proposta.model.Carteira;
import br.com.zupacademy.gabrielamartins.proposta.model.GatewayPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
    Optional<Carteira> findByCarteiraAndCartaoId(GatewayPagamento carteira, Long id);
}
