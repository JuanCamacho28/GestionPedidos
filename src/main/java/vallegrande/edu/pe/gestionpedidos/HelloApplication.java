package vallegrande.edu.pe.gestionpedidos.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/vallegrande/edu/pe/gestionpedidos/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 450);
        primaryStage.setTitle("Gestión de Pedidos - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}