package com.formacionbdi.springboot.backendAngularapirestHotel.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	 @Query("SELECT c FROM Cliente c WHERE c.idClient NOT IN " +
	            "(SELECT r.cliente.idClient FROM Reserva r WHERE r.estado = 'Activa')")
	 public List<Cliente> findClientesSinReservaActiva();
}
