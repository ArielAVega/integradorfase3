package com.vegasystems.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vegasystems.dto.GeneroDTO;
import com.vegasystems.dto.PeliculaDTO;
import com.vegasystems.dto.mapper.GeneroMapper;
import com.vegasystems.dto.mapper.PeliculaMapper;
import com.vegasystems.entity.Genero;
import com.vegasystems.repository.IGeneroRepository;
import com.vegasystems.service.IGeneroService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@AllArgsConstructor
public class GeneroServiceImp implements IGeneroService{
	private  IGeneroRepository generoRepository;
	private  PeliculaMapper peliculaMapper;
	private  GeneroMapper generoMapper;
	

	@Override
	public List<PeliculaDTO> obtenerPorGenero(String genero) {
		Genero generoHallado= generoRepository.findByNombreGenero(genero);
		
		List<PeliculaDTO> peliculas = generoHallado.getPeliculas().stream().map(
				p->{
					PeliculaDTO dto = peliculaMapper.peliculaToPeliculaDTO(p);
					return dto;
				}).collect(Collectors.toList());
		return peliculas;
	}


	@Override
	public List<GeneroDTO> obtenerGeneros() {
		List<Genero> generos = generoRepository.findAll();
		List<GeneroDTO> generosDTO = generos.stream().map(
				g->{
					GeneroDTO generoDTO = generoMapper.generoToGeneroDTO(g);
					return generoDTO;
				}).collect(Collectors.toList());
		
		return generosDTO;
	}

	@Override
	public GeneroDTO guardarGenero(GeneroDTO generoDTO) {
		Genero genero = generoRepository.save(
				generoMapper.generoDTOtoGenero(generoDTO));
		GeneroDTO generoDTOGuardado = generoMapper.generoToGeneroDTO(genero);
		return generoDTOGuardado;
	}

	@Override
	public Boolean eliminarGenero(Integer id) {
		Boolean isDelete = false;
		Optional<Genero> genero = generoRepository.findById(id);
		if(genero.isPresent()) {
			generoRepository.deleteById(id);
			isDelete=true;
		}
		return isDelete;
	}

	@Override
	public GeneroDTO obtenerPorId(Integer id) {
		Optional<Genero> genero = generoRepository.findById(id);
		GeneroDTO generoPorDevolver = null;
		if(genero.isPresent()) {
		 generoPorDevolver = generoMapper.generoToGeneroDTO(genero.get());
		}
		return generoPorDevolver;
	}
}


