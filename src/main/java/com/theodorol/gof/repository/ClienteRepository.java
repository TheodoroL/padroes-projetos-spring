package com.theodorol.gof.repository;

import com.theodorol.gof.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository  extends CrudRepository<Cliente, Long> {
}
