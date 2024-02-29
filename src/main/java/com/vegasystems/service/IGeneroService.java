package com.vegasystems.service;

import java.util.List;

import com.vegasystems.dto.GeneroDTO;
import com.vegasystems.dto.PeliculaDTO;

public interface IGeneroService {
	
	List<PeliculaDTO> obtenerPorGenero(String genero);
	
	List<GeneroDTO> obtenerGeneros();
	GeneroDTO guardarGenero(GeneroDTO generoDTO);
	Boolean eliminarGenero(Integer id);
	GeneroDTO obtenerPorId(Integer id);
}

