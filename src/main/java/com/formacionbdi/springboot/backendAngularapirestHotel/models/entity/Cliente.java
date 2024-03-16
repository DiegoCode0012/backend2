package com.formacionbdi.springboot.backendAngularapirestHotel.models.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_client")
	private Long idClient;
	@Size(min=3,max=12 ,message="debe contener al menos 3 a 12 caracteres")
	@NotEmpty
	private String name;
	@Size(min=3,max=9 ,message="debe contener entre 3 a 12 caracteres")
	@NotEmpty
	private String lastname;
    @Pattern(regexp="^[0-9]+$", message="debe contener solo números")
    @Size(min=9, max=9, message="debe tener exactamente 9 dígitos")
    @NotEmpty
	private String phone;
	@Column(unique=true)
    @Pattern(regexp="^[0-9]+$", message="debe contener solo números")
    @Size(min=8, max=8, message="debe tener exactamente 8 dígitos")
	@NotEmpty
	private String dni;
}
