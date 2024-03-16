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
import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Cliente;
import com.formacionbdi.springboot.backendAngularapirestHotel.services.IClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> getAll() {
	return clienteService.getAllClientes();
	}
	
	
	@GetMapping("/clientesSinReserva")
	public List<Cliente> getAllSinReserva() {
	return clienteService.getAllClientesSinReservaActiva();
	}
	
	@GetMapping("/clientes/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public Cliente ver(@PathVariable Long id) {
	return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	public  ResponseEntity<?> create(@Valid @RequestBody Cliente cliente,BindingResult result) {
		Cliente clienteNew= null;
		Map<String,Object> response =new HashMap<>();
		  if (result.hasFieldErrors()) {
	            return validation(result);
	        }
			try {
				clienteNew =clienteService.save(cliente);
			} catch (DataIntegrityViolationException  e) {//comprobamos los campos unicos
				List <String> errors= new ArrayList<String>();
				errors.add("el campo dni debe ser unico");
				response.put("errors", errors);				
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
			}
			response.put("cliente", clienteNew);
			response.put("mensaje", "Cliente creado con exito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		   
	}
	
	@DeleteMapping("/clientes/{id}")
	public void delete(@PathVariable Long id) {
		clienteService.deleteCliente(id);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result,@PathVariable Long id) {
		Map<String,Object> response =new HashMap<>();
		if (result.hasFieldErrors()) {
	            return validation(result);
	        }
		Cliente clienteUpdate=clienteService.findById(id);
		if(clienteUpdate==null) {
			response.put("mensaje","tipo no encontrado en BBDD");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		try {
			clienteUpdate.setName(cliente.getName());
			clienteUpdate.setLastname(cliente.getLastname());
			clienteUpdate.setDni(cliente.getDni());
			clienteUpdate.setPhone(cliente.getPhone());		
		} catch (DataIntegrityViolationException  e) {// comprobamos los campos unicos
			List <String> errors= new ArrayList<String>();
			response.put("mensaje", "dni debe ser unico");
			response.put("errors", errors);				
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CONFLICT);
		}
		response.put("cliente", clienteUpdate);
		response.put("mensaje", "El cliente ha sido actualizado con exito");
		clienteService.save(clienteUpdate);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
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
