package application;

import javafx.beans.property.SimpleStringProperty;

public class Plataforma {
	
	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty fabricante;

	public Plataforma(String nombre, String fabricante) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.fabricante = new SimpleStringProperty(fabricante);
	}
	
	public Plataforma(int id, String nombre, String fabricante) {
		super();
		this.id = id;
		this.nombre = new SimpleStringProperty(nombre);
		this.fabricante = new SimpleStringProperty(fabricante);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNombre() {
		return nombre.get();
	}

	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}

	public void setFabricante(String fabricante) {
		this.fabricante = new SimpleStringProperty(fabricante);
	}

	public String getFabricante() {
		return fabricante.get();
	}
}
