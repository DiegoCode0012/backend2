package com.formacionbdi.springboot.backendAngularapirestHotel.models.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="tipos")
public class Tipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double precio;
	
	@NotNull
    @Pattern(regexp = "ESTANDAR|DOBLE|SUITE|FAMILIAR", message = "debe ser ESTANDAR,DOBLE,SUITE O FAMILIAR")
    @Column(unique = true)
	private String descripcion;
	
	public double PrecioHabitacion(String x) {
		double precio = 0;
		if(x.equalsIgnoreCase("ESTANDAR")) {
			precio=50.0;
		}else if (x.equalsIgnoreCase("DOBLE")) {
			precio=100.0;
		}else if (x.equalsIgnoreCase("SUITE")) {
			precio=400.0;
		}else if(x.equalsIgnoreCase("FAMILIAR")){
			precio=250.0;
		}
		return precio;
	}
}
