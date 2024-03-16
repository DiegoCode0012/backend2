package com.formacionbdi.springboot.backendAngularapirestHotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.dao.IClienteDao;
import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Cliente;

@Service
public class ClienteServiceImp implements IClienteService {

	@Autowired
	private IClienteDao clienteRepository;
	@Override
	public List<Cliente> getAllClientesSinReservaActiva() {
		return clienteRepository.findClientesSinReservaActiva();
	}

	@Override
	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);		
	}

	@Override
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public List<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

}
