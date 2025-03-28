package com.theodorol.gof.repository;

import com.theodorol.gof.model.Cliente;
import com.theodorol.gof.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, String> {
}
