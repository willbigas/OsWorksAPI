package br.com.willbigas.osworksapi.api.controller;

import br.com.willbigas.osworksapi.api.model.OrdemServicoInputModel;
import br.com.willbigas.osworksapi.api.model.OrdemServicoModel;
import br.com.willbigas.osworksapi.domain.model.OrdemServico;
import br.com.willbigas.osworksapi.domain.service.OrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServicoModel criar(@Valid @RequestBody OrdemServicoInputModel ordemServicoInputModel) {
        OrdemServico ordemServico = toEntity(ordemServicoInputModel);
        return toModel(ordemServicoService.criar(ordemServico));
    }

    @GetMapping
    public List<OrdemServicoModel> listar() {
        return toCollectionModel(ordemServicoService.buscarTodos());
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServicoModel> buscar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoService.buscarPorID(ordemServicoId);

        if (ordemServico != null) {
            OrdemServicoModel ordemServicoModel = toModel(ordemServico);
            return ResponseEntity.ok(ordemServicoModel);
        }

        return ResponseEntity.notFound().build();

    }

    private OrdemServicoModel toModel(OrdemServico ordemServico) {
        return modelMapper.map(ordemServico, OrdemServicoModel.class);
    }

    private List<OrdemServicoModel> toCollectionModel(List<OrdemServico> ordemServicos) {
        return ordemServicos.stream()
                .map(ordemServico -> toModel(ordemServico))
                .collect(Collectors.toList());
    }

    private OrdemServico toEntity(OrdemServicoInputModel ordemServicoInputModel) {
        return modelMapper.map(ordemServicoInputModel, OrdemServico.class);
    }
}
