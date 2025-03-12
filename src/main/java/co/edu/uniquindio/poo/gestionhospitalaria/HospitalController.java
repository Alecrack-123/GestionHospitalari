package co.edu.uniquindio.poo.gestionhospitalaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlador principal para la gestión hospitalaria.
 * Maneja la administración de médicos, pacientes y citas en el hospital.
 */

public class HospitalController {
    private Hospital hospital;
    private ObservableList<Medico> listaMedicos;
    private ObservableList<Paciente> listaPacientes;
    private ObservableList<Cita> listaCitas;
    private Map<String, Medico> mapaMedicos;
    private Map<String, Paciente> mapaPacientes;

    /**
     * Constructor de la clase HospitalController.
     * Inicializa el hospital y configura los parámetros del sistema.
     *
     * @param nombre Nombre del hospital
     * @param horarioAtencion Horario de atención del hospital
     * @param maxPacientes Número máximo de pacientes permitidos
     * @param reglasFacturacion Reglas de facturación aplicadas en el hospital
     */

    public HospitalController(String nombre, String horarioAtencion, int maxPacientes, String reglasFacturacion) {
        this.hospital = new Hospital(nombre);
        GestorConfiguracion config = GestorConfiguracion.getInstance();
        config.setHorarioAtencion(horarioAtencion);
        config.setNumeroMaxPacientes(maxPacientes);
        config.setReglasFacturacion(reglasFacturacion);
        this.listaMedicos = FXCollections.observableArrayList();
        this.listaPacientes = FXCollections.observableArrayList();
        this.listaCitas = FXCollections.observableArrayList();
        this.mapaMedicos = new HashMap<>();
        this.mapaPacientes = new HashMap<>();
    }


    // MÉTODOS PARA MÉDICOS

    /**
     * Agrega un médico al hospital.
     *
     * @param nombre Nombre del médico
     * @param especialidad Especialidad del médico
     * @param maxPacientes Número máximo de pacientes que puede atender
     */

    public void agregarMedico(String nombre, String especialidad, int maxPacientes) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del médico no puede estar vacío.");
        }
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del médico no puede estar vacía.");
        }
        if (maxPacientes <= 0) {
            throw new IllegalArgumentException("El número máximo de pacientes debe ser mayor que cero.");
        }
        if (mapaMedicos.containsKey(nombre.toLowerCase())) {
            throw new IllegalArgumentException("El médico ya está registrado.");
        }
        Medico medico = new Medico(nombre, especialidad, maxPacientes);
        listaMedicos.add(medico);
        mapaMedicos.put(nombre.toLowerCase(), medico);
    }

    /**
     * Elimina un médico del sistema.
     *
     * @param medico Objeto del médico a eliminar
     */
    public void eliminarMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        listaMedicos.remove(medico);
        mapaMedicos.remove(medico.getNombre().toLowerCase());
    }
    /**
     * Modifica la información de un médico.
     *
     * @param medico Objeto del médico a modificar
     * @param nuevoNombre Nuevo nombre del médico
     * @param nuevaEspecialidad Nueva especialidad del médico
     * @param nuevoMaxPacientes Nuevo número máximo de pacientes
     */
    public void modificarMedico(Medico medico, String nuevoNombre, String nuevaEspecialidad, int nuevoMaxPacientes) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        mapaMedicos.remove(medico.getNombre().toLowerCase());
        medico.setNombre(nuevoNombre);
        medico.setEspecialidad(nuevaEspecialidad);
        medico.setMaxPacientes(nuevoMaxPacientes);
        mapaMedicos.put(nuevoNombre.toLowerCase(), medico);
    }
    /**
     * Obtiene la lista de médicos registrados en el hospital.
     *
     * @return Lista observable de médicos
     */
    public ObservableList<Medico> getListaMedicos() {
        return listaMedicos;
    }
    // MÉTODOS PARA PACIENTES

    /**
     * Agrega un paciente al hospital.
     *
     * @param nombre Nombre del paciente
     * @param edad Edad del paciente
     * @param historial Historial médico del paciente
     * @param medicamentos Medicamentos actuales del paciente
     */


    public void agregarPaciente(String nombre, int edad, String historial, String medicamentos) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del paciente no puede estar vacío.");
        }
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad del paciente debe ser mayor que cero.");
        }
        if (historial == null || historial.trim().isEmpty()) {
            throw new IllegalArgumentException("El historial médico no puede estar vacío.");
        }
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("Los medicamentos no pueden estar vacíos.");
        }
        if (mapaPacientes.containsKey(nombre.toLowerCase())) {
            throw new IllegalArgumentException("El paciente ya está registrado.");
        }
        Paciente paciente = new Paciente(nombre, edad, historial, medicamentos);
        listaPacientes.add(paciente);
        mapaPacientes.put(nombre.toLowerCase(), paciente);
    }



    public void eliminarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        listaPacientes.remove(paciente);
        mapaPacientes.remove(paciente.getNombre().toLowerCase());
    }


    public void modificarPaciente(Paciente paciente, String nuevoNombre, int nuevaEdad, String nuevoHistorial, String nuevosMedicamentos) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        mapaPacientes.remove(paciente.getNombre().toLowerCase());
        paciente.setNombre(nuevoNombre);
        paciente.setEdad(nuevaEdad);
        paciente.setHistorialEnfermedades(nuevoHistorial);
        paciente.setMedicamentos(nuevosMedicamentos);
        mapaPacientes.put(nuevoNombre.toLowerCase(), paciente);
    }

    public ObservableList<Paciente> getListaPacientes() {
        return listaPacientes;
    }
    /**
     * Agenda una cita médica.
     *
     * @param paciente Paciente que solicita la cita
     * @param medico Médico que atenderá la cita
     * @param fecha Fecha de la cita
     * @param hora Hora de la cita
     * @return Objeto de la cita creada
     */
    public Cita agendarCita(Paciente paciente, Medico medico, LocalDate fecha, String hora) {
        Cita nuevaCita = new Cita(paciente, medico, fecha, hora);
        listaCitas.add(nuevaCita);
        return nuevaCita;
    }

    /**
     * Cancela una cita médica.
     *
     * @param cita Cita a cancelar
     */

    public void cancelarCita(Cita cita) {
        listaCitas.remove(cita);
    }

    public List<Cita> listarCitasPorFecha(LocalDate fecha) {
        return listaCitas.stream()
                .filter(cita -> cita.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<String> buscarNombresPalindromos(List<String> nombres) {
        return nombres.stream()
                .filter(nombre -> nombre.equalsIgnoreCase(new StringBuilder(nombre).reverse().toString()))
                .collect(Collectors.toList());
    }

    public List<String> buscarNombresConDosVocalesIguales(List<String> nombres) {
        return nombres.stream()
                .filter(nombre -> nombre.toLowerCase().matches(".*([aeiou]).*\\1.*"))
                .collect(Collectors.toList());
    }

    public Cita getListaCitas() {
        return null;
    }
}





