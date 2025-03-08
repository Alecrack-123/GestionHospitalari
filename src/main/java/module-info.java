module co.edu.uniquindio.poo.gestionhospitalaria {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.gestionhospitalaria to javafx.fxml;
    exports co.edu.uniquindio.poo.gestionhospitalaria;
}