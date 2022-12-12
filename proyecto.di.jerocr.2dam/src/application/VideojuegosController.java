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
	private TextField txtNombreP;
	@FXML
	private TextField txtDesarrolladora;
	@FXML
	private TextField txtFabricante;
	@FXML
	private ChoiceBox<String> cbPlataforma;
	@FXML
	private ChoiceBox<Integer> cbPegi;
	@FXML
	private Button botonAnadir;
	@FXML
	private Button botonBorrar;
	@FXML
	private Button botonAnadirP;
	@FXML
	private Button botonBorrarP;
	@FXML
	private TableView<Videojuego> tableJuego;
	@FXML
	private TableView<Plataforma> tablePlataforma;
	@FXML
	private TableColumn<Videojuego, String> columNombre;
	@FXML
	private TableColumn<Videojuego, String> columDesarrolladora;
	@FXML
	private TableColumn<Plataforma, String> columNombreP;
	@FXML
	private TableColumn<Plataforma, String> columFabricante;
	@FXML
	private TableColumn<Videojuego, Integer> columPlataforma;
	@FXML
	private TableColumn<Videojuego, String> columPegi;

	private ObservableList<Videojuego> listaVideojuegos = FXCollections.observableArrayList();
	
	private ObservableList<Plataforma> listaPlataformas = FXCollections.observableArrayList();

	public ObservableList<Integer> pegis = FXCollections.observableArrayList(0, 3, 7, 12, 16, 18);

	@FXML
	private void initialize() {
		cbPlataforma.setItems(getNombresPlataformasBD(getPlataformasBD()));
		cbPegi.setItems(pegis);
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columDesarrolladora.setCellValueFactory(new PropertyValueFactory<>("desarrolladora"));
		columPlataforma.setCellValueFactory(new PropertyValueFactory<>("plataformaId"));
		columPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));
		columNombreP.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columFabricante.setCellValueFactory(new PropertyValueFactory<>("fabricante"));

		ObservableList<Videojuego> listaVideojuegosDB = getVideojuegosBD();

		tableJuego.setItems(listaVideojuegosDB);

		ObservableList<Plataforma> listaPlataformasDB = getPlataformasBD();

		tablePlataforma.setItems(listaPlataformasDB);

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
				Videojuego vj = new Videojuego(rs.getInt("id"), rs.getString("nombre"), rs.getString("desarrolladora"),
						rs.getInt("pegi"), rs.getInt("plataforma_id"));
				listaVideojuegosBD.add(vj);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaVideojuegosBD;

	}

	private ObservableList<String> getNombresPlataformasBD(ObservableList<Plataforma> plataformas) {

		ObservableList<String> nombrePlataformas = FXCollections.observableArrayList();

		for (int i = 0; i < plataformas.size(); i++) {
			nombrePlataformas.add(plataformas.get(i).getNombre());
		}

		return nombrePlataformas;

	}

	private ObservableList<Plataforma> getPlataformasBD() {

		ObservableList<Plataforma> listaPlataformasBD = FXCollections.observableArrayList();

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();

		String query = "select * from plataformas";

		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plataforma p = new Plataforma(rs.getInt("id"), rs.getString("nombre"), rs.getString("fabricante"));
				listaPlataformasBD.add(p);
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaPlataformasBD;

	}

	@FXML
	public void anadirVideojuego(ActionEvent event) {

		int nuevoId = 0;

		if (!txtNombre.getText().isBlank() && !txtDesarrolladora.getText().isBlank()
				&& !cbPlataforma.getSelectionModel().isEmpty() && !cbPegi.getSelectionModel().isEmpty()) {

			DatabaseConnection dbConnection1 = new DatabaseConnection();
			Connection connection1 = dbConnection1.getConnection();
			String query1 = "select id from plataformas where nombre ='" + cbPlataforma.getValue().toString() + "'";
			try {
				PreparedStatement ps = connection1.prepareStatement(query1);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					nuevoId = rs.getInt("id");
				}

				connection1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Videojuego v = new Videojuego(txtNombre.getText(), txtDesarrolladora.getText(), nuevoId, cbPegi.getValue());
			listaVideojuegos.add(v);

			txtNombre.clear();
			txtDesarrolladora.clear();
			cbPlataforma.getSelectionModel().clearSelection();
			cbPegi.getSelectionModel().clearSelection();

			try {

				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				String query = "insert into videojuegos (nombre, desarrolladora, pegi, plataforma_id) VALUES (?,?,?,?)";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, v.getNombre());
				ps.setString(2, v.getDesarrolladora());
				ps.setInt(3, v.getPlataformaId());
				ps.setInt(4, v.getPegi());
				ps.executeUpdate();
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ObservableList<Videojuego> listaVideojuegosDB = getVideojuegosBD();

			tableJuego.setItems(listaVideojuegosDB);

		} else {
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("No se puede insertar");
			alerta.setHeaderText("No se puede dejar ningún campo en blanco");
			alerta.setContentText("Por favor introduzca todos los campos correctamente");
			alerta.showAndWait();
		}
	}
	
	@FXML
	public void anadirPlataforma(ActionEvent event) {


		if (!txtNombreP.getText().isBlank() && !txtFabricante.getText().isBlank()) {

			Plataforma p = new Plataforma(txtNombreP.getText(),txtFabricante.getText());
			listaPlataformas.add(p);
			txtNombreP.clear();
			txtFabricante.clear();


			try {

				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				String query = "insert into plataformas (nombre, fabricante) VALUES (?,?)";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, p.getNombre());
				ps.setString(2, p.getFabricante());
				ps.executeUpdate();
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ObservableList<Plataforma> listaPlataformaDB = getPlataformasBD();

			tablePlataforma.setItems(listaPlataformaDB);
			
			cbPlataforma.setItems(getNombresPlataformasBD(getPlataformasBD()));

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
			alerta.setHeaderText("No se ha seleccionado ningun videojuego a borrar");
			alerta.setContentText("Por favor, selecciona un videojuego");
			alerta.showAndWait();
		} else {
//			tableJuego.getItems().remove(indiceSeleccionado);

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "delete from videojuegos where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Videojuego v = tableJuego.getSelectionModel().getSelectedItem();
				ps.setInt(1, v.getId());
				ps.executeUpdate();
				tableJuego.getSelectionModel().clearSelection();

				ObservableList<Videojuego> listaVideojuegosDB = getVideojuegosBD();

				tableJuego.setItems(listaVideojuegosDB);

				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@FXML
	public void borrarPlataforma(ActionEvent event) {
		System.out.println("Borrando una Plataforma");
		int indiceSeleccionado = tablePlataforma.getSelectionModel().getSelectedIndex();

		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error al borrar");
			alerta.setHeaderText("No se ha seleccionado ningun plataforma a borrar");
			alerta.setContentText("Por favor, selecciona un plataforma");
			alerta.showAndWait();
		} else {
//			tableJuego.getItems().remove(indiceSeleccionado);

			DatabaseConnection dbConnection1 = new DatabaseConnection();
			Connection connection1 = dbConnection1.getConnection();

			try {
				String query1 = "delete from videojuegos where plataforma_id = ?";
				PreparedStatement ps1 = connection1.prepareStatement(query1);

				ps1.setInt(1, tablePlataforma.getSelectionModel().getSelectedItem().getId());
				ps1.executeUpdate();
				tableJuego.getSelectionModel().clearSelection();

				ObservableList<Videojuego> listaVideojuegosDB = getVideojuegosBD();

				tableJuego.setItems(listaVideojuegosDB);

				connection1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "delete from plataformas where id = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				Plataforma p = tablePlataforma.getSelectionModel().getSelectedItem();
				ps.setInt(1, p.getId());
				ps.executeUpdate();
				tablePlataforma.getSelectionModel().clearSelection();

				ObservableList<Plataforma> listaPlataformaDB = getPlataformasBD();

				tablePlataforma.setItems(listaPlataformaDB);

				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
