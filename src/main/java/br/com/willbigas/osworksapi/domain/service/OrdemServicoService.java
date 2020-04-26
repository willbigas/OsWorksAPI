package br.com.willbigas.osworksapi.domain.service;

import br.com.willbigas.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.willbigas.osworksapi.domain.exception.NegocioException;
import br.com.willbigas.osworksapi.domain.model.Cliente;
import br.com.willbigas.osworksapi.domain.model.Comentario;
import br.com.willbigas.osworksapi.domain.model.OrdemServico;
import br.com.willbigas.osworksapi.domain.model.enums.StatusOrdemServico;
import br.com.willbigas.osworksapi.domain.repository.ClienteRepository;
import br.com.willbigas.osworksapi.domain.repository.ComentarioRepository;
import br.com.willbigas.osworksapi.domain.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ComentarioRepository comentarioRepository;

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

    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        Comentario comentario = new Comentario();
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada")));
        return comentarioRepository.save(comentario);
    }

}
