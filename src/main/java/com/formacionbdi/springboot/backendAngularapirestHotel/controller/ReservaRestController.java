package com.formacionbdi.springboot.backendAngularapirestHotel.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Reserva;
import com.formacionbdi.springboot.backendAngularapirestHotel.services.IReservaService;

import jakarta.validation.Valid;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ReservaRestController {


	@Autowired
	private IReservaService reservaService;
	
	@GetMapping("/reservas")
	public List<Reserva> getAll() {
	return reservaService.findAll();
	}
	
	
	@PostMapping("/reservas")
	public ResponseEntity<?> create(@Valid @RequestBody Reserva reserva, BindingResult result) {
		Reserva reservaNuevo;
		Map<String,Object> response =new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors=result.getFieldErrors()
					.stream()
					.map(err->
				 "El campo " + err.getField() + "  " + err.getDefaultMessage()
				)
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);

		}
		reservaNuevo =reservaService.save(reserva);
		response.put("reserva", reservaNuevo);
		response.put("mensaje", "Reserva creado con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/reservas/{id}")
	public void delete(@PathVariable Long id) {
		  reservaService.deleteReserva(id);
	}
	
	
}
