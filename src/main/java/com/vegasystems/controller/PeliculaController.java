package com.vegasystems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vegasystems.dto.ResumenPeliculaDTO;
import com.vegasystems.service.IPeliculaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/movies")
@AllArgsConstructor
public class PeliculaController {
	private IPeliculaService peliculaService;
	
	@PostMapping(value = "/save",consumes = {MediaType.APPLICATION_JSON_VALUE,
											 MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<ResumenPeliculaDTO> registrarPelicula(@RequestPart("movie") String movie,
																@RequestPart("file") List<MultipartFile> file){
		ResumenPeliculaDTO resumenPeliculaDTO = peliculaService.registrarPelicula(movie, file.get(0));
		
		return new ResponseEntity<>(resumenPeliculaDTO,HttpStatus.CREATED);
	}
}
