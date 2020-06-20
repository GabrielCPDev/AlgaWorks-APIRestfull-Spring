package com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel.ordemDeServicoAPI.ordemDeServicoAPI.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Gabriel");
		cliente1.setEmail("gabriel@gabriel.com");
		cliente1.setTelefone("(34) 9 9999-9999");
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Rafael");
		cliente2.setEmail("rafael@rafael.com");
		cliente2.setTelefone("(34) 8 8888-8888");
		return Arrays.asList(cliente1, cliente2);
	}
}
