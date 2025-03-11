package co.edu.uniquindio.poo.gestionhospitalaria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
            GestorConfiguracion.inicializar("08:00 - 18:00", 50, "Reglas estándar");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/gestionhospitalaria/Hospital.fxml"));
            Parent root = fxmlLoader.load(); // Cargar solo una vez
            primaryStage.setTitle("Gestión Hospitalaria");
            primaryStage.setScene(new Scene(root, 800, 600)); // Definir escena con root
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar la interfaz gráfica.");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
