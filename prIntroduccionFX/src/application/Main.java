package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

//			EJEMPLO DE UN STACK PANEL
//			Button btn = new Button("Clic aquí");
//			btn.setOnAction(new EventHandler<ActionEvent>() {
//				public void handle(ActionEvent event) {
//					System.out.println("¡Hola Mundo!");
//				}
//			});
//			
//			Label lbl = new Label("¡Hola Mundo!");
//			
//			StackPane panel = new StackPane();
//			
//			panel.setAlignment(lbl,Pos.TOP_CENTER);
//			panel.setAlignment(btn, Pos.CENTER);
//			panel.getChildren().addAll(lbl,btn);
//			FIN EJEMPLO DE UN STACK PANEL

			Button btn1 = new Button("Botón 1");
			Button btn2 = new Button("Botón 2");
			Button btn3 = new Button("Botón 3");
			Button btn4 = new Button("Botón 4");

//			VBox panelVertical = new VBox(15);
//			panelVertical.setPadding(new Insets(15));
//			panelVertical.getChildren().addAll(btn1, btn2, btn3);

//			EJEMPLO DE UN VBOX PANEL
//			VBox panel = new VBox(15);
//			panel.setPadding(new Insets(15));
//			panel.getChildren().addAll(btn1, btn2, btn3);
//			FIN EJEMPLO DE UN VBOX PANEL

//			EJEMPLO DE UN BORDER PANE
//			BorderPane panel = new BorderPane();
//			panel.setRight(panelVertical);
//			FIN EJEMPLO DE UN BORDER PANE

			GridPane panel = new GridPane();
			
			panel.setVgap(15);
			panel.setHgap(15);
			panel.setPadding(new Insets(15));
			
			panel.add(btn1, 0, 0);
			panel.add(btn2, 1, 0);
			panel.add(btn3, 0, 1);
			panel.add(btn4, 1, 1);
			
			Scene scene = new Scene(panel, 400, 300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Introducción a JavaFX");
			primaryStage.getIcons().add(new Image("/application/imagenes/java.png"));
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
