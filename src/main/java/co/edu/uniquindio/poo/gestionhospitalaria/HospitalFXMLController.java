package co.edu.uniquindio.poo.gestionhospitalaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class HospitalFXMLController {
    private HospitalController hospitalController;

    @FXML
    private TextField txtNombreMedico, txtEspecialidad, txtMaxPacientes;
    @FXML
    private TextField txtNombrePaciente, txtEdad, txtHistorial, txtMedicamentos;
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

        cargarMedicos();
        cargarPacientes();
        cargarCitas();
    }


    @FXML
    private void agregarMedico() {
        try {
            String nombre = txtNombreMedico.getText();
            String especialidad = txtEspecialidad.getText();
            int maxPacientes = Integer.parseInt(txtMaxPacientes.getText());

            Medico medico = new Medico(nombre, especialidad, maxPacientes);
            hospitalController.agregarMedico(medico);
            listaMedicos.add(medico);

            System.out.println("Médico agregado correctamente: " + medico);
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
    private void actualizarMedico() {
        Medico medico = cbMedicos.getValue();
        if (medico != null) {
            try {
                medico.setNombre(txtNombreMedico.getText());
                medico.setEspecialidad(txtEspecialidad.getText());
                medico.setMaxPacientes(Integer.parseInt(txtMaxPacientes.getText()));
                tablaMedicos.refresh();
                System.out.println("Médico actualizado: " + medico);
            } catch (NumberFormatException e) {
                System.out.println("Error: Máximo de pacientes debe ser un número válido.");
            }
        } else {
            System.out.println("Error: No se seleccionó un médico.");
        }
    }

    @FXML
    private void agregarPaciente() {
        try {
            String nombre = txtNombrePaciente.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            String historial = txtHistorial.getText();
            String medicamentos = txtMedicamentos.getText();

            Paciente paciente = new Paciente(nombre, edad, historial, medicamentos);
            hospitalController.agregarPaciente(paciente);
            listaPacientes.add(paciente);

            System.out.println("Paciente agregado correctamente: " + paciente);
            limpiarCamposPaciente();
        } catch (NumberFormatException e) {
            System.out.println("Error: La edad debe ser un número válido.");
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
    private void actualizarPaciente() {
        Paciente paciente = cbPacientes.getValue();
        if (paciente != null) {
            try {
                paciente.setNombre(txtNombrePaciente.getText());
                paciente.setEdad(Integer.parseInt(txtEdad.getText()));
                paciente.setHistorialEnfermedades(txtHistorial.getText());
                paciente.setMedicamentos(txtMedicamentos.getText());
                tablaPacientes.refresh();
                System.out.println("Paciente actualizado: " + paciente);
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad debe ser un número válido.");
            }
        } else {
            System.out.println("Error: No se seleccionó un paciente.");
        }
    }

    @FXML
    private void agendarCita() {
        LocalDate fecha = dpFechaCita.getValue();
        String hora = txtHoraCita.getText();
        Medico medico = cbMedicos.getValue();
        Paciente paciente = cbPacientes.getValue();

        if (fecha == null || hora.isEmpty() || medico == null || paciente == null) {
            System.out.println("Error: Todos los campos de la cita son obligatorios.");
            return;
        }

        Cita cita = new Cita(paciente, medico, fecha, hora);
        hospitalController.agendarCita(cita);
        listaCitas.add(cita);
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
