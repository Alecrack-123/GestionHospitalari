package co.edu.uniquindio.poo.gestionhospitalaria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class Cita {
    private Paciente paciente;
    private Medico medico;
    private LocalDate fecha;
    private String hora;

    public Cita(Paciente paciente,Medico medico, LocalDate fecha, String hora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
    }

    public void reservarCita() {
        System.out.println("Cita reservada para " + paciente.getNombre() + " el " + fecha + " a las " + hora);
    }

    public void cancelarCita() {
        System.out.println("Cita cancelada para " + paciente.getNombre() + " el " + fecha + " a las " + hora);
    }

    public void listarCita() {
        System.out.println("Cita: " + paciente.getNombre() + " el " + fecha + " a las " + hora);
    }

    // Getters y Setters
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente pacientes) {
        this.paciente = pacientes;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}

