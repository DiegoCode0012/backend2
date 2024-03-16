package com.formacionbdi.springboot.backendAngularapirestHotel.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Habitacion;
import com.formacionbdi.springboot.backendAngularapirestHotel.services.IHabitacionService;
import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class HabitacionRestController {

	@Autowired
	private IHabitacionService habitacionService;
	
	@GetMapping("/habitaciones")
	public List<Habitacion> getAll(){
		return habitacionService.getAllHabitaciones();
	}
	
	@GetMapping("/habitacionesdisponibles")
	public List<Habitacion> HabitacionesDisponibles(){
		return habitacionService.getAllAvailableRooms();
	}
	
	@GetMapping("/habitaciones/{id}")
	public Habitacion ver(@PathVariable Long id){
		return habitacionService.findHabitacionById(id);
	}
	
	@PostMapping("/habitaciones")
	public ResponseEntity<?> create(@Valid  @RequestBody Habitacion habitacion,  BindingResult result) {
		Habitacion habitacionNuevo;
		Map<String,Object> response =new HashMap<>();
		if (result.hasFieldErrors()) {
	            return validation(result);
	        }
		
		try {
			habitacionNuevo =habitacionService.save(habitacion);
		} catch (DataIntegrityViolationException e) {//comprobamos los campos unicos
			List <String> errors= new ArrayList<String>();
			errors.add("el campo numero debe ser unico");
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
		}
		
		response.put("habitacion", habitacionNuevo);
		response.put("mensaje", "Habitación creado con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/habitaciones/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Habitacion habitacion, BindingResult result,@PathVariable Long id) {
		Map<String,Object> response =new HashMap<>();

		if (result.hasFieldErrors()) {
	            return validation(result);
	        }
		Habitacion habitacionUpdate=habitacionService.findHabitacionById(id);

		if(habitacionUpdate==null) {
			response.put("mensaje","tipo no encontrado en BBDD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			habitacionUpdate.setNumero(habitacion.getNumero());
			habitacionUpdate.setTipo(habitacion.getTipo());
			habitacionService.save(habitacionUpdate);		
			} catch (DataIntegrityViolationException e) {//comprobamos los campos unicos
			List <String> errors= new ArrayList<String>();
			errors.add("el campo numero debe ser unico");
			response.put("mensaje", "Error al realizar el insert en BBDD");
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
		}
		response.put("room", habitacionUpdate);
		response.put("mensaje", "La habitacion ha sido creado con exito");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/habitaciones/{id}")
	public void deleteHabitacion(@PathVariable Long id) {
		habitacionService.deletebyId(id);
	}
	
	@GetMapping("/habitaciones/idTipo/{id}")
	public List<Habitacion> filtrarHabitacionesxTipo(@PathVariable Long id){
		return habitacionService.getAllAvailableRooms().stream().filter(x->x.getTipo().getId()==id).toList();
	}
	
	private ResponseEntity<?> validation(BindingResult result) {
        Map<String, Object> response = new HashMap<>();

    List <String> errors= result.getFieldErrors().stream().map(err->
	 "El campo " + err.getField() + "  " + err.getDefaultMessage()
	).toList();
        response.put("errors", errors);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
    }
}
