package com.sermaluc.registry.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

	@NotBlank(message = "Rut is mandatory")
    private String rut;

    @NotBlank(message = "Nombre is mandatory")
    @Size(max = 50, message = "Nombre must not exceed 50 characters")
    private String nombre;

    @NotBlank(message = "Apellido is mandatory")
    @Size(max = 50, message = "Apellido must not exceed 50 characters")
    private String apellido;

    @NotNull(message = "Fecha de nacimiento is mandatory")
    @Past(message = "Fecha de nacimiento must be in the past")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "Dirección is mandatory")
    private String direccion;

    @NotBlank(message = "Calle is mandatory")
    private String calle;

	
}