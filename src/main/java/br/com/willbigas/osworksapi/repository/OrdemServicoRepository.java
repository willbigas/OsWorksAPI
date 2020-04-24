package br.com.willbigas.osworksapi.repository;

import br.com.willbigas.osworksapi.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico , Long> {


}
