package com.vegasystems.peliculas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vegasystems.entity.Genero;
import com.vegasystems.entity.ImagenPelicula;
import com.vegasystems.entity.Pelicula;
import com.vegasystems.repository.IGeneroRepository;
import com.vegasystems.repository.IPeliculaRepository;

@SpringBootTest
public class AltaPeliculaTest {
	@Autowired
	private IGeneroRepository generoRepository;
	@Autowired
	private IPeliculaRepository peliculaRepository;
	
	@Test
	public void validarNuevaPelicula() {
		try {
			// insnancias de generos
			//GENEROS
			Genero genero = new Genero();
			genero.setNombreGenero("Animacion");			
			Genero genero1 = generoRepository.save(genero);
			
			genero = new Genero();
			genero.setNombreGenero("Fantasia");			
			Genero genero2 = generoRepository.save(genero);
			
			// instancia de pelicula
			Pelicula pelicula = new Pelicula();
			pelicula.setTitulo("Super Mario: La película");
			pelicula.setUrlWeb("https://m.cinesargentinos.com.ar/pelicula/9043-super-mario-bros-la-pelicula/");
			List<Genero> generos = new ArrayList<>();
			generos.add(genero1);
			generos.add(genero2);
			pelicula.setGeneros(generos);
			
			InputStream inputStream = getClass().getResourceAsStream("./resources/mario.jpg");
			ImagenPelicula imagenPelicula = new ImagenPelicula();
			imagenPelicula.setNombreArchivo("mario.jpg");
			imagenPelicula.setImagen(inputStream.readAllBytes());

			pelicula.setImagenPelicula(imagenPelicula);
			
			Pelicula peliculaGuardada = peliculaRepository.save(pelicula);
			
			List<Pelicula> peliculas = peliculaRepository.findAll();
			
			assertTrue(!peliculas.isEmpty() && peliculaGuardada.getId()==peliculas.get(0).getId());
			
			peliculaRepository.delete(peliculaGuardada);
			
			assertTrue(peliculaRepository.findAll().isEmpty());
			
			generoRepository.deleteAll();
			assertTrue(generoRepository.findAll().isEmpty());
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
