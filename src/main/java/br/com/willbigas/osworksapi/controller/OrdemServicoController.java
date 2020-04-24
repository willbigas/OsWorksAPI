package br.com.willbigas.osworksapi.controller;

import br.com.willbigas.osworksapi.model.OrdemServico;
import br.com.willbigas.osworksapi.service.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoService.buscarTodos();
    }

    @GetMapping("/{ordemServicoId}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long ordemServicoId) {
        OrdemServico ordemServico = ordemServicoService.buscarPorID(ordemServicoId);

        if (ordemServico != null) {
            return ResponseEntity.ok(ordemServico);
        }

        return ResponseEntity.notFound().build();

    }
}
