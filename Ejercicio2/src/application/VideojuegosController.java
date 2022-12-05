package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VideojuegosController {

	@FXML
	private TextField txtNombre;
	@FXML
	private TextField txtPrecio;
	@FXML
	private ChoiceBox<String> cbConsola;
	@FXML
	private ChoiceBox<String> cbPegi;
	@FXML
	private Button botonAnadir;
	@FXML
	private Button botonBorrar;
	@FXML
	private TableView<Videojuego> tableJuego;
	@FXML
	private TableColumn<Videojuego, String> columNombre;
	@FXML
	private TableColumn<Videojuego, Integer> columPrecio;
	@FXML
	private TableColumn<Videojuego, String> columConsola;
	@FXML
	private TableColumn<Videojuego, String> columPegi;

	private ObservableList<Videojuego> listaVideojuegos = FXCollections
			.observableArrayList(new Videojuego("Fifa 23", 49, "PS5", "PEGI 3"));

	public ObservableList<String> consolas = FXCollections.observableArrayList("PS5", "PS4", "Nintendo", "XBOX");
	public ObservableList<String> pegis = FXCollections.observableArrayList("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16",
			"PEGI 18");

	@FXML
	private void initialize() {
		cbConsola.setItems(consolas);
		cbPegi.setItems(pegis);
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		ObservableList<Videojuego> listaVideojuegosDB = getVideojuegosBD();

		tableJuego.setItems(listaVideojuegosDB);

	}

	private ObservableList<Videojuego> getVideojuegosBD() {

		ObservableList<Videojuego> listaVideojuegosBD = FXCollections.observableArrayList();

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from videojuegos";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Videojuego vj = new Videojuego(rs.getString("nombre"), rs.getInt("precio"), rs.getString("consola"),
						rs.getString("pegi"));
				listaVideojuegosBD.add(vj);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaVideojuegosBD;

	}

	@FXML
	public void anadirVideojuego(ActionEvent event) {

		if (!txtNombre.getText().isBlank() && !txtPrecio.getText().isBlank() && !cbConsola.getSelectionModel().isEmpty()
				&& !cbPegi.getSelectionModel().isEmpty()) {
			if (esNumero(txtPrecio.getText())) {
				Videojuego v = new Videojuego(txtNombre.getText(), Integer.parseInt(txtPrecio.getText()),
						cbConsola.getValue().toString(), cbPegi.getValue());

				listaVideojuegos.add(v);

				txtNombre.clear();
				txtPrecio.clear();
				cbConsola.getSelectionModel().clearSelection();
				cbPegi.getSelectionModel().clearSelection();
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en el campo del precio");
				alerta.setContentText("Por favor introduzca un precio correcto");
				alerta.showAndWait();

				/**
				 * SI NO SE INTRODUCEN TODOS LOS DATOS SE MOSTRARÁ UNA ALERTA DE TIPO WARNING
				 * AVISANDO QUE FALTAN DATOS
				 */
			}
		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("No se puede insertar");
			alerta.setHeaderText("No se puede dejar ningún campo en blanco");
			alerta.setContentText("Por favor introduzca todos los campos correctamente");
			alerta.showAndWait();
		}
	}

	@FXML
	public void borrarVideojuego(ActionEvent event) {
		System.out.println("Borrando un videojuego");
		int indiceSeleccionado = tableJuego.getSelectionModel().getSelectedIndex();

		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningun libro a borrar");
			alerta.setContentText("Por favor, selecciona un videojuego");
			alerta.showAndWait();
		} else {
			tableJuego.getItems().remove(indiceSeleccionado);
			tableJuego.getSelectionModel().clearSelection();
		}

	}

	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
