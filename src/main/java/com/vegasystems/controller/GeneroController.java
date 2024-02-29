package com.vegasystems.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vegasystems.dto.GeneroDTO;
import com.vegasystems.service.IGeneroService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/genres")
@AllArgsConstructor
public class GeneroController {
	private IGeneroService generoService;
	
	@PostMapping("/save")
	public ResponseEntity<GeneroDTO> guardarGenero(@RequestBody GeneroDTO generoDTO){
		GeneroDTO generoGuardado =  generoService.guardarGenero(generoDTO);
		return new ResponseEntity<>(generoGuardado,HttpStatus.CREATED);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<GeneroDTO>> obtenerGeneros(){
		List<GeneroDTO> generos = generoService.obtenerGeneros();
		return ResponseEntity.ok(generos);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HashMap<String, Boolean>> eliminarGenero(@PathVariable Integer id){
		Boolean isDelete = generoService.eliminarGenero(id);
		HashMap<String, Boolean> estadoGeneroEliminado = new HashMap<>();
		estadoGeneroEliminado.put("eliminado", isDelete);
		return ResponseEntity.ok(estadoGeneroEliminado);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<GeneroDTO> actualizarGenero(@PathVariable Integer id, 
														@RequestBody GeneroDTO generoDTO){
		GeneroDTO generoBuscado = generoService.obtenerPorId(id);
		GeneroDTO generoActualizado = generoService.guardarGenero(generoDTO);
		return new ResponseEntity<>(generoActualizado, HttpStatus.ACCEPTED);
	}
}


