package co.edu.uniquindio.poo.gestionhospitalaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;



public class HospitalFXMLController {
    private HospitalController hospitalController;


    @FXML
    private TextField txtNombreMedico, txtEspecialidad, txtMaxPacientes;
    @FXML
    private TextField txtNombrePaciente, txtEdad ;
    @FXML
    private TextArea txtHistorial, txtMedicamentos;
    @FXML
    private DatePicker dpFechaCita;
    @FXML
    private TextField txtHoraCita;
    @FXML
    private ComboBox<Medico> cbMedicos;
    @FXML
    private TableView<Medico> tablaMedicos;
    @FXML
    private TableColumn<Medico, String> colNombreMedico, colEspecialidad;
    @FXML
    private TableColumn<Medico, Integer> colMaxPacientes;
    @FXML
    private TableView<Paciente> tablaPacientes;
    @FXML
    private TableColumn<Paciente, String> colNombrePaciente, colHistorial, colMedicamentos;
    @FXML
    private TableColumn<Paciente, Integer> colEdad;
    @FXML
    private ComboBox<Paciente> cbPacientes;
    @FXML
    private TableView<Cita> tablaCitas;
    @FXML
    private TableColumn<Cita, String> colPacienteCita, colMedicoCita, colHoraCita;
    @FXML
    private TableColumn<Cita, LocalDate> colFechaCita;
    @FXML
    private Button btnAgregarMedico, btnAgregarPaciente, btnAgendarCita, btnEliminarCita;
    @FXML
    private Label lblResultadoMedico;
    @FXML
    private Label lblResultadoPaciente;
    @FXML
    private ComboBox<Paciente> cbPacientesCitas;
    @FXML
    private ComboBox<Medico> cbMedicosCitas;




    private ObservableList<Medico> listaMedicos = FXCollections.observableArrayList();
    private ObservableList<Paciente> listaPacientes = FXCollections.observableArrayList();
    private ObservableList<Cita> listaCitas = FXCollections.observableArrayList();


    public void initialize() {
        hospitalController = new HospitalController("Hospital Central", "8:00 AM - 6:00 PM", 50, "Reglas estándar");

        colNombreMedico.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colMaxPacientes.setCellValueFactory(new PropertyValueFactory<>("maxPacientes"));

        colNombrePaciente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colHistorial.setCellValueFactory(new PropertyValueFactory<>("historialEnfermedades"));
        colMedicamentos.setCellValueFactory(new PropertyValueFactory<>("medicamentos"));

        colPacienteCita.setCellValueFactory(new PropertyValueFactory<>("paciente"));
        colMedicoCita.setCellValueFactory(new PropertyValueFactory<>("medico"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHoraCita.setCellValueFactory(new PropertyValueFactory<>("hora"));

        tablaMedicos.setItems(listaMedicos);
        tablaPacientes.setItems(listaPacientes);
        tablaCitas.setItems(listaCitas);
        cbMedicos.setItems(listaMedicos);
        cbPacientes.setItems(listaPacientes);

        cbMedicos.setItems(listaMedicos);
        cbMedicosCitas.setItems(listaMedicos);
        cbPacientes.setItems(listaPacientes);
        cbPacientesCitas.setItems(listaPacientes);


        cargarMedicos();
        cargarPacientes();
        cargarCitas();
    }

    // METODOS DE MEDICO
    @FXML
    private void agregarMedico() {
        try {
            String nombre = txtNombreMedico.getText();
            String especialidad = txtEspecialidad.getText();
            int maxPacientes = Integer.parseInt(txtMaxPacientes.getText());

            hospitalController.agregarMedico(nombre, especialidad, maxPacientes);
            listaMedicos.add(new Medico(nombre, especialidad, maxPacientes));

            System.out.println("Médico agregado correctamente: " + nombre);
            limpiarCamposMedico();
        } catch (NumberFormatException e) {
            System.out.println("Error: El campo 'Máximo de Pacientes' debe ser un número.");
        }
    }


    @FXML
    private void eliminarMedico() {
        Medico medico = cbMedicos.getValue();
        if (medico != null) {
            hospitalController.eliminarMedico(medico);
            listaMedicos.remove(medico);
            System.out.println("Médico eliminado: " + medico);
        } else {
            System.out.println("Error: No se seleccionó un médico.");
        }
    }


    @FXML
    private void modificarMedico() {
        Medico medicoSeleccionado = cbMedicos.getValue();
        if (medicoSeleccionado != null) {
            try {
                String nuevoNombre = txtNombreMedico.getText();
                String nuevaEspecialidad = txtEspecialidad.getText();
                int nuevoMaxPacientes = Integer.parseInt(txtMaxPacientes.getText());

                hospitalController.modificarMedico(medicoSeleccionado, nuevoNombre, nuevaEspecialidad, nuevoMaxPacientes);
                tablaMedicos.refresh();
                System.out.println("Médico modificado: " + medicoSeleccionado);
            } catch (NumberFormatException e) {
                System.out.println("Error: Máximo de pacientes debe ser un número válido.");
            }
        } else {
            System.out.println("Error: No se seleccionó un médico.");
        }
    }





    // METODOS DE PACIENTE
    @FXML
    private void agregarPaciente() {
        String nombre = txtNombrePaciente.getText();
        String edadTexto = txtEdad.getText();
        String historial = txtHistorial.getText();
        String medicamentos = txtMedicamentos.getText();

        if (nombre.isEmpty() || edadTexto.isEmpty()) {
            lblResultadoPaciente.setText("Debe ingresar nombre y edad.");
            return;
        }

        try {
            int edad = Integer.parseInt(edadTexto);
            Paciente nuevoPaciente = new Paciente(nombre, edad, historial, medicamentos);
            listaPacientes.add(nuevoPaciente);
            limpiarCamposPaciente();
            lblResultadoPaciente.setText("Paciente agregado correctamente.");
        } catch (NumberFormatException e) {
            lblResultadoPaciente.setText("La edad debe ser un número válido.");
        }
    }

    @FXML
    private void eliminarPaciente() {
        Paciente paciente = cbPacientes.getValue();
        if (paciente != null) {
            hospitalController.eliminarPaciente(paciente);
            listaPacientes.remove(paciente);
            System.out.println("Paciente eliminado: " + paciente);
        } else {
            System.out.println("Error: No se seleccionó un paciente.");
        }
    }

    @FXML
    private void modificarPaciente() {
        Paciente pacienteSeleccionado = cbPacientes.getValue();
        if (pacienteSeleccionado != null) {
            try {
                String nuevoNombre = txtNombrePaciente.getText();
                int nuevaEdad = Integer.parseInt(txtEdad.getText());
                String nuevoHistorial = txtHistorial.getText();
                String nuevosMedicamentos = txtMedicamentos.getText();

                hospitalController.modificarPaciente(pacienteSeleccionado, nuevoNombre, nuevaEdad, nuevoHistorial, nuevosMedicamentos);
                tablaPacientes.refresh();
                System.out.println("Paciente modificado: " + pacienteSeleccionado);
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad debe ser un número válido.");
            }
        } else {
            System.out.println("Error: No se seleccionó un paciente.");
        }
    }


    // METODOS DE CITA
    @FXML
    private void agendarCita() {
        LocalDate fecha = dpFechaCita.getValue();
        String hora = txtHoraCita.getText();
        Medico medico = cbMedicosCitas.getValue();
        Paciente paciente = cbPacientesCitas.getValue();

        if (fecha == null || hora.isEmpty() || medico == null || paciente == null) {
            return;
        }

        Cita nuevaCita = hospitalController.agendarCita(paciente, medico, fecha, hora);
        listaCitas.add(nuevaCita);
    }



    @FXML
    private void cancelarCita() {
        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada != null) {
            hospitalController.cancelarCita(citaSeleccionada);
            listaCitas.remove(citaSeleccionada);
            System.out.println("Cita eliminada: " + citaSeleccionada);
        } else {
            System.out.println("Error: No se seleccionó una cita.");
        }
    }

    // METODOS ADICIONALES

    @FXML
    private void buscarNombresPalindromos() {
        List<String> nombres = listaPacientes.stream().map(Paciente::getNombre).toList();
        List<String> nombresPalindromos = new ArrayList<>();

        for (String nombre : nombres) {
            String nombreInvertido = new StringBuilder(nombre).reverse().toString();
            if (nombre.equalsIgnoreCase(nombreInvertido)) {
                nombresPalindromos.add(nombre);
            }
        }

        mostrarResultado("Nombres Palíndromos", nombresPalindromos);
    }

    @FXML
    private void buscarNombresConDosVocalesIguales() {
        List<String> nombres = listaPacientes.stream().map(Paciente::getNombre).toList();
        List<String> nombresConVocalesIguales = new ArrayList<>();

        for (String nombre : nombres) {
            int[] contadorVocales = new int[5]; // Contadores para a, e, i, o, u

            for (char c : nombre.toLowerCase().toCharArray()) {
                switch (c) {
                    case 'a' -> contadorVocales[0]++;
                    case 'e' -> contadorVocales[1]++;
                    case 'i' -> contadorVocales[2]++;
                    case 'o' -> contadorVocales[3]++;
                    case 'u' -> contadorVocales[4]++;
                }
            }

            for (int count : contadorVocales) {
                if (count >= 2) {
                    nombresConVocalesIguales.add(nombre);
                    break;
                }
            }
        }

        mostrarResultado("Nombres con dos vocales iguales", nombresConVocalesIguales);
    }

    private void mostrarResultado(String mensaje, List<String> lista) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(mensaje);

        if (lista.isEmpty()) {
            alert.setContentText("No se encontraron coincidencias.");
        } else {
            alert.setContentText(String.join("\n", lista));
        }

        alert.showAndWait();
    }



    @FXML
    private void listarCitas() {
        listaCitas.setAll(hospitalController.getListaCitas());
    }

    private void cargarMedicos() {
        listaMedicos.setAll(hospitalController.getListaMedicos());
    }

    private void cargarPacientes() {
        listaPacientes.setAll(hospitalController.getListaPacientes());
    }

    private void cargarCitas() {
        listaCitas.setAll(hospitalController.getListaCitas());
    }

    private void limpiarCamposMedico() {
        txtNombreMedico.clear();
        txtEspecialidad.clear();
        txtMaxPacientes.clear();
    }

    private void limpiarCamposPaciente() {
        txtNombrePaciente.clear();
        txtEdad.clear();
        txtHistorial.clear();
        txtMedicamentos.clear();
    }
}
