package com.formacionbdi.springboot.backendAngularapirestHotel.services;

import java.util.List;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> getAllClientes();
	public void deleteCliente(Long id);
	public Cliente save(Cliente tipo);
	public Cliente findById(Long id);
	public List<Cliente> getAllClientesSinReservaActiva();
}
