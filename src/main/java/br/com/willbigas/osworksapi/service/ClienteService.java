package br.com.willbigas.osworksapi.service;

import br.com.willbigas.osworksapi.exception.NegocioException;
import br.com.willbigas.osworksapi.model.Cliente;
import br.com.willbigas.osworksapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findFirstByEmail(cliente.getEmail());

        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com este email");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}

