package com.formacionbdi.springboot.backendAngularapirestHotel.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Reserva;


public interface IReservaDao extends JpaRepository<Reserva, Long>{

}
