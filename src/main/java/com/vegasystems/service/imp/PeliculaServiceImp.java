package com.vegasystems.service.imp;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.ResumenPeliculaDTO;
import com.vegasystems.dto.mapper.PeliculaMapper;
import com.vegasystems.entity.Pelicula;
import com.vegasystems.repository.IPeliculaRepository;
import com.vegasystems.service.IPeliculaService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PeliculaServiceImp implements IPeliculaService{
	private PeliculaMapper peliculaMapper;
	private IPeliculaRepository peliculaRepository;

	@Override
	public ResumenPeliculaDTO registrarPelicula(String movie, MultipartFile archivoImagen) {
		ResumenPeliculaDTO resumenPeliculaDTO = new ResumenPeliculaDTO();
		PeliculaDTO peliculaDTO = new PeliculaDTO();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// mapeo de los parámetros al DTO de Pelicula
			peliculaDTO = objectMapper.readValue(movie, PeliculaDTO.class);
			peliculaDTO.setImagenPelicula(archivoImagen.getBytes());
			
			//mapeo del DTO a la entidad y registro en la base de datos
			Pelicula pelicula = peliculaRepository.save(peliculaMapper.peliculaDtoToPelicula(peliculaDTO));
			
			resumenPeliculaDTO = peliculaMapper.peliculaToResumenPeliculaDTO(pelicula);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return resumenPeliculaDTO;
	}

}
