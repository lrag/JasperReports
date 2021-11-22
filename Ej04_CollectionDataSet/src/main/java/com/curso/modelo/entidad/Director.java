package com.curso.modelo.entidad;

public class Director {

	private Integer id;
	private String nombre;
	private String pais;
	private String fechaNacimiento;

	public Director() {
		super();
	}

	public Director(Integer id, String nombre, String pais, String fechaNacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Director [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", fechaNacimiento=" + fechaNacimiento
				+ "]";
	}
	
}
