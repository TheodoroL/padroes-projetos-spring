package com.theodorol.gof.service.impl;

import com.theodorol.gof.model.Cliente;
import com.theodorol.gof.model.Endereco;
import com.theodorol.gof.repository.ClienteRepository;
import com.theodorol.gof.repository.EnderecoRepository;
import com.theodorol.gof.service.ClienteService;
import com.theodorol.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;
    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("cliente invalido"));
    }

    @Override
    public void inserirCliente(Cliente cliente) {
        salvarClienteCep(cliente);
    }

    @Override
    public void atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if(clienteDb.isEmpty()){
            throw new RuntimeException("cliente invalido");
        }
        salvarClienteCep(cliente);
    }

    @Override
    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteCep(Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(()->{
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    enderecoRepository.save(novoEndereco);
                    return novoEndereco;
                });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }
}
