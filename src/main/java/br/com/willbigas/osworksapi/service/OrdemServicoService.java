package br.com.willbigas.osworksapi.service;

import br.com.willbigas.osworksapi.exception.NegocioException;
import br.com.willbigas.osworksapi.model.Cliente;
import br.com.willbigas.osworksapi.model.OrdemServico;
import br.com.willbigas.osworksapi.model.enums.StatusOrdemServico;
import br.com.willbigas.osworksapi.repository.ClienteRepository;
import br.com.willbigas.osworksapi.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository
                .findById(ordemServico
                        .getCliente()
                        .getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);

    }

    public List<OrdemServico> buscarTodos() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico buscarPorID(Long id) {
        return ordemServicoRepository.findById(id).orElse(null);
    }

}
