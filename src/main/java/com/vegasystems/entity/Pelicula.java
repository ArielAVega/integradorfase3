package com.vegasystems.entity;

import java.util.List;

import lombok.Data;

@Data
public class Pelicula {
	private Integer id;
	private String titulo;
	private String urlWeb;
	private ImagenPelicula imagenPelicula;
	private List<Genero> generos;
}

