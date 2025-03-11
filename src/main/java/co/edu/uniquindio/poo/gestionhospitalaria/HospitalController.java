package co.edu.uniquindio.poo.gestionhospitalaria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class HospitalController {
    private Hospital hospital;
    private ObservableList<Medico> listaMedicos;
    private ObservableList<Paciente> listaPacientes;
    private ObservableList<Cita> listaCitas;

    public HospitalController(String nombre, String horarioAtencion, int maxPacientes, String reglasFacturacion) {
        this.hospital = new Hospital(nombre);
        GestorConfiguracion.getInstance().setHorarioAtencion(horarioAtencion);
        GestorConfiguracion.getInstance().setNumeroMaxPacientes(maxPacientes);
        GestorConfiguracion.getInstance().setReglasFacturacion(reglasFacturacion);
        this.listaMedicos = FXCollections.observableArrayList();
        this.listaPacientes = FXCollections.observableArrayList();
        this.listaCitas = FXCollections.observableArrayList();
    }

    // MÉTODOS PARA MÉDICOS
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
        Medico medico = new Medico(nombre, especialidad, maxPacientes);
        listaMedicos.add(medico);
    }

    public void eliminarMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        listaMedicos.remove(medico);
    }

    public void actualizarMedico(Medico medico, String nuevoNombre, String nuevaEspecialidad, int nuevoMaxPacientes) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo.");
        }
        medico.setNombre(nuevoNombre);
        medico.setEspecialidad(nuevaEspecialidad);
        medico.setMaxPacientes(nuevoMaxPacientes);
    }

    public ObservableList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    // MÉTODOS PARA PACIENTES
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
        Paciente paciente = new Paciente(nombre, edad, historial, medicamentos);
        listaPacientes.add(paciente);
    }

    public void eliminarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        listaPacientes.remove(paciente);
    }

    public void actualizarPaciente(Paciente paciente, String nuevoNombre, int nuevaEdad, String nuevoHistorial, String nuevosMedicamentos) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        paciente.setNombre(nuevoNombre);
        paciente.setEdad(nuevaEdad);
        paciente.setHistorialEnfermedades(nuevoHistorial);
        paciente.setMedicamentos(nuevosMedicamentos);
    }

    public ObservableList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    // MÉTODO PARA AGENDAR CITA
    public void agendarCita(Paciente paciente, LocalDate fecha, String hora) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la cita no puede ser nula.");
        }
        if (hora == null || hora.trim().isEmpty()) {
            throw new IllegalArgumentException("La hora de la cita no puede estar vacía.");
        }
        
        Cita cita = new Cita(paciente, (Medico) listaMedicos, fecha, hora);
        listaCitas.add(cita);
    }

    public ObservableList<Cita> getListaCitas() {
        return listaCitas;
    }

    public Medico buscarMedicoPorNombre(String nombre) {
        return listaMedicos.stream()
                .filter(medico -> medico.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public Paciente buscarPacientePorNombre(String nombre) {
        return listaPacientes.stream()
                .filter(paciente -> paciente.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public List<Cita> buscarCitasPorFecha(LocalDate fecha) {
        return listaCitas.stream()
                .filter(cita -> cita.getFecha().equals(fecha))
                .toList();
    }

    public void cancelarCita(Cita cita) {
        listaCitas.remove(cita);
    }

    public List<Cita> listarCitasPorFecha(LocalDate fecha) {
        return listaCitas.stream()
                .filter(cita -> cita.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    public List<Paciente> obtenerPacientesConNombrePalindromo() {
        return listaPacientes.stream()
                .filter(paciente -> esPalindromo(paciente.getNombre()))
                .collect(Collectors.toList());
    }

    public List<Paciente> obtenerPacientesConVocalesRepetidas() {
        return listaPacientes.stream()
                .filter(paciente -> tieneDosVocalesIguales(paciente.getNombre()))
                .collect(Collectors.toList());
    }

    private boolean esPalindromo(String nombre) {
        String limpio = nombre.toLowerCase().replaceAll("\\s+", "");
        return limpio.equals(new StringBuilder(limpio).reverse().toString());
    }

    private boolean tieneDosVocalesIguales(String nombre) {
        String limpio = nombre.toLowerCase();
        return "aeiou".chars().anyMatch(v -> limpio.chars().filter(c -> c == v).count() >= 2);
    }

    public void agendarCita(Cita cita) {
    }

    public void agregarPaciente(Paciente paciente) {
    }

    public void agregarMedico(Medico medico) {
    }
}

