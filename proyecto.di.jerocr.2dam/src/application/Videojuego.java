package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Videojuego {

	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty desarrolladora;
	private SimpleIntegerProperty pegi;
	private SimpleIntegerProperty plataformaId;

	public Videojuego() {
		
	}
	
	public Videojuego(String nombre, String desarrolladora, int pegi, int plataformaId) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.desarrolladora = new SimpleStringProperty(desarrolladora);
		this.pegi = new SimpleIntegerProperty(pegi);
		this.plataformaId = new SimpleIntegerProperty(plataformaId);

	}

	public Videojuego(int id, String nombre, String desarrolladora, int pegi, int plataformaId) {
		super();
		this.id = id;
		this.nombre = new SimpleStringProperty(nombre);
		this.desarrolladora = new SimpleStringProperty(desarrolladora);
		this.pegi = new SimpleIntegerProperty(pegi);
		this.plataformaId = new SimpleIntegerProperty(plataformaId);

	}

	public String getNombre() {
		return nombre.get();
	}
	
	public int getId() {
		return this.id;
	}

	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}

	public void setDesarrolladora(String desarrolladora) {
		this.nombre = new SimpleStringProperty(desarrolladora);
	}

	public String getDesarrolladora() {
		return desarrolladora.get();
	}

	public int getPegi() {
		return pegi.get();
	}

	public void setPegi(int pegi) {
		this.pegi = new SimpleIntegerProperty(pegi);
	}

	public int getPlataformaId() {
		return plataformaId.get();
	}

	public void setPlataformaId(int plataformaId) {
		this.pegi = new SimpleIntegerProperty(plataformaId);
	}

}