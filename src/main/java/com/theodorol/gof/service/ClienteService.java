package com.theodorol.gof.service;

import com.theodorol.gof.model.Cliente;

public interface ClienteService {
    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(Long id);
    void inserirCliente(Cliente cliente);
    void atualizarCliente(Long id, Cliente cliente);
    void deletarCliente(Long id);

}
