package com.formacionbdi.springboot.backendAngularapirestHotel.models.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.formacionbdi.springboot.backendAngularapirestHotel.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	  	Optional<Usuario> findByUsername(String nombreUsuario);
	    Optional<Usuario> findByUsernameOrEmail(String nombreUsuario, String email);
	    boolean existsByUsername(String nombreUsuario);
	    boolean existsByEmail(String email);
}
