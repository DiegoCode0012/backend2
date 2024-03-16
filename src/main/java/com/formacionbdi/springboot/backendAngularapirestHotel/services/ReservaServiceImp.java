package com.formacionbdi.springboot.backendAngularapirestHotel.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.dao.IReservaDao;
import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Reserva;



@Component
public class ReservaServiceImp implements IReservaService {

	@Autowired
	private IReservaDao reservaDao;
	@Override
	public Reserva save(Reserva reserva) {
		Date date = new Date();
		reserva.setDiaStart(date);
//		log.info("hora proveniente del datapicker"+ reserva.getDiaEnd());
		//HORA FINAL POR DEFECTO A LAS 6 DE LA MAÑANA
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(reserva.getDiaEnd());
		calendar.set(Calendar.HOUR_OF_DAY,6);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		Date fechaFinal=calendar.getTime();
		reserva.setDiaEnd(fechaFinal);
		return reservaDao.save(reserva);
	}

	@Override
	public Reserva findReservaById(Long id) {
		return reservaDao.findById(id).orElse(null);
	}

	@Override
	public List<Reserva> findAll() {
		return reservaDao.findAll();
	}

	@Override
	public void deleteReserva(Long id) {
		reservaDao.deleteById(id);
	}

}
