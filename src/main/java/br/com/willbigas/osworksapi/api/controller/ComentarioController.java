package br.com.willbigas.osworksapi.api.controller;

import br.com.willbigas.osworksapi.api.model.ComentarioInputModel;
import br.com.willbigas.osworksapi.api.model.ComentarioModel;
import br.com.willbigas.osworksapi.domain.exception.EntidadeNaoEncontradaException;
import br.com.willbigas.osworksapi.domain.model.Comentario;
import br.com.willbigas.osworksapi.domain.model.OrdemServico;
import br.com.willbigas.osworksapi.domain.repository.OrdemServicoRepository;
import br.com.willbigas.osworksapi.domain.service.OrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não foi encontrada"));
        return toCollectionModel(ordemServico.getComentarios());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioModel adicionar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInputModel comentarioInputModel) {
        Comentario comentario = ordemServicoService.adicionarComentario(ordemServicoId, comentarioInputModel.getDescricao());
        return toModel(comentario);
    }

    private ComentarioModel toModel(Comentario comentario) {
        return modelMapper.map(comentario, ComentarioModel.class);
    }

    private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}
