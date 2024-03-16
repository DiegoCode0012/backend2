package com.formacionbdi.springboot.backendAngularapirestHotel.services;

import java.util.List;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Reserva;


public interface IReservaService {
	public Reserva save(Reserva reserva);
	public Reserva findReservaById(Long id);
	public List<Reserva> findAll();
	public void deleteReserva(Long id);
}
