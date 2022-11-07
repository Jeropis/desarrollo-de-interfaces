package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ejercicio1 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		VBox panel = new VBox(15);
		panel.setPadding(new Insets(15));
		Label lbl_nombre = new Label("Nombre");
		TextField txt_nombre = new TextField();
		Label lbl_contrasena = new Label("Contraseña");
		PasswordField psw_contrasena = new PasswordField();
		Button btn = new Button("Entrar");
		Label lbl_bienvenida = new Label();
		panel.getChildren().addAll(lbl_nombre, txt_nombre, lbl_contrasena, psw_contrasena, btn, lbl_bienvenida);
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				lbl_bienvenida.setText("Bienvenid@ " + txt_nombre.getText() + " su contraseña es: " + psw_contrasena.getText());

			}

		});

		Scene scene = new Scene(panel, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Introducción a JavaFX");
		primaryStage.getIcons().add(new Image("/application/imagenes/java.png"));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}