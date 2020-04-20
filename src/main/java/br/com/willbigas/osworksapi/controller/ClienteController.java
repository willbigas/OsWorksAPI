package br.com.willbigas.osworksapi.controller;

import br.com.willbigas.osworksapi.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        Cliente cliente1 = new Cliente(1L, "João", "joãodascouves@algaworks.com", "34 9999-1111");
        Cliente cliente2 = new Cliente(2L, "Maria", "mariadasilva@gmail.com", "48 9999-8888");
        return Arrays.asList(cliente1, cliente2);
    }
}
