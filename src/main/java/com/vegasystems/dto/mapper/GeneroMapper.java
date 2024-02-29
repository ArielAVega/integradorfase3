package com.vegasystems.dto.mapper;

import org.mapstruct.Mapper;

import com.vegasystems.dto.GeneroDTO;
import com.vegasystems.entity.Genero;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GeneroMapper {
	@Mapping(target = "peliculas", ignore = true)
	Genero generoDTOtoGenero(GeneroDTO generoDTO);
	GeneroDTO generoToGeneroDTO(Genero genero);
}

