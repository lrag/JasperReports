package com.curso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.curso.modelo.entidad.Director;
import com.curso.modelo.entidad.Pelicula;

public class PeliculasBeanFactory {

	public static Collection<Pelicula> getPeliculas(){
		
		Director d1 = new Director(1, "John McTiernan", "USA", "8 de enero de 1951");
		Director d2 = new Director(2, "Stanley Kubrick", "USA", "26 de julio de 1928");
		Director d3 = new Director(3, "Ridley Scott", "Reino unido", "30 de noviembre de 1937");
		Director d4 = new Director(4, "Clint Eastwood", "USA", "31 de mayo de 1930");
		Director d5 = new Director(5, "Steven Spielberg", "USA", "18 de diciembre de 1946");
		Director d6 = new Director(6, "Sergio Leone", "Italia", "3 de enero de 1929");
		Director d7 = new Director(7, "Richard Donner", "USA", "24 de abril de 1930");
		Director d8 = new Director(8, "Mel Brooks", "USA", "28 de junio de 1926");
		
		Pelicula p1  = new Pelicula(1, "Die Hard", d1, "Acción", 1988);
		Pelicula p2  = new Pelicula(2, "2001: A Space Odyssey", d2, "CI-FI", 1968);
		Pelicula p3  = new Pelicula(3, "Alien", d3, "CI-FI, Susto", 1979);
		Pelicula p4  = new Pelicula(4, "El sargento de hierro", d4, "Bélica", 1986);		
		Pelicula p5  = new Pelicula(5, "El último grán heroe", d1, "Acción", 1993);
		Pelicula p6  = new Pelicula(6, "El resplandor", d2, "Terror", 1980);
		Pelicula p7  = new Pelicula(7, "Ruta suicida", d3, "Acción", 1977);		
		Pelicula p8  = new Pelicula(8, "Depredador", d1, "CI-FI", 1987);
		Pelicula p9  = new Pelicula(9, "Raiders of the Lost Ark", d5, "Acción", 1981);
		Pelicula p10 = new Pelicula(10, "Tiburón", d5, "Acción", 1975);
		Pelicula p11 = new Pelicula(11, "El bueno, el feo y el malo", d6, "Western", 1966);		
		Pelicula p12 = new Pelicula(12, "Scrooged", d7, "Comedia", 1988);
		Pelicula p13 = new Pelicula(13, "EL jovencito Frankenstein", d8, "Comedia", 1974);

		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(p1);
		peliculas.add(p2);
		peliculas.add(p3);
		peliculas.add(p4);
		peliculas.add(p5);
		peliculas.add(p6);
		peliculas.add(p7);
		peliculas.add(p8);
		peliculas.add(p9);
		peliculas.add(p10);
		peliculas.add(p11);
		peliculas.add(p12);
		peliculas.add(p13);
		
		return peliculas;		
	}
	
}
