package com.vegasystems.generos;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vegasystems.dto.GeneroDTO;
import com.vegasystems.entity.Genero;
import com.vegasystems.repository.IGeneroRepository;
import com.vegasystems.service.IGeneroService;

@SpringBootTest
public class CRUDGeneroTest {
	@Autowired
	private IGeneroService generoService;
	@Autowired
	private IGeneroRepository generoRepository;
	@Test
	public void validarCRUD(){
		GeneroDTO generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Fantasía");
		GeneroDTO generoDTO1 = generoService.guardarGenero(generoDTO);
		
		generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Animación");
		GeneroDTO generoDTO2 = generoService.guardarGenero(generoDTO);
		
		generoDTO = new GeneroDTO();
		generoDTO.setNombreGenero("Super Heroes");
		GeneroDTO generoDTO3 = generoService.guardarGenero(generoDTO);
		
		//verificación de registro de los tres generos
		assertTrue(generoService.obtenerGeneros().size()==3);
		
		//validación de consulta por id
		GeneroDTO generoBuscado = generoService.obtenerPorId(generoDTO1.getId());
		
		assertTrue(generoBuscado.getNombreGenero().equals(generoDTO1.getNombreGenero()));
		
		//validación de modificación de géneros (a Terror)
		GeneroDTO otroGenero = generoService.obtenerPorId(generoDTO2.getId());
		otroGenero.setNombreGenero("Terror");
		
		GeneroDTO generoModificado = generoService.guardarGenero(otroGenero);
		assertTrue(generoDTO2.getId().equals(generoModificado.getId()) &&
					!generoDTO2.getNombreGenero().endsWith(generoModificado.getNombreGenero()));
		
		// validación de eliminación de los tres géneros
		Boolean isDelete1 = generoService.eliminarGenero(generoDTO1.getId());
		Boolean isDelete2 = generoService.eliminarGenero(generoModificado.getId());
		Boolean isDelete3 = generoService.eliminarGenero(generoDTO3.getId());
		
		assertTrue(isDelete1 && isDelete2 && isDelete3);
		
		assertTrue(generoService.obtenerGeneros().isEmpty());
		
	}
}
