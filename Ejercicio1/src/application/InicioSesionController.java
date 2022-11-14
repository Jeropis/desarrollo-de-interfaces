package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InicioSesionController {
	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private PasswordField pswContraseña;
	
	@FXML
	private Label lblMensaje;
	
	@FXML
	public void entrar(ActionEvent event) {
		
		lblMensaje.setText("Bienvenido " + txtNombre.getText() + " al aula virtual");
		txtNombre.setText("");
		pswContraseña.setText("");
		
	}
	
	
}
