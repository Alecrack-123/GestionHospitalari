package co.edu.uniquindio.poo.gestionhospitalaria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;

public class Cita {
    private Paciente pacientes;
    private Medico medico;
    private LocalDate fecha;
    private String hora;

    public Cita(Paciente pacientes,Medico medico, LocalDate fecha, String hora) {
        this.pacientes = pacientes;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
    }

    public void reservarCita() {
        System.out.println("Cita reservada para " + pacientes.getNombre() + " el " + fecha + " a las " + hora);
    }

    public void cancelarCita() {
        System.out.println("Cita cancelada para " + pacientes.getNombre() + " el " + fecha + " a las " + hora);
    }

    public void listarCita() {
        System.out.println("Cita: " + pacientes.getNombre() + " el " + fecha + " a las " + hora);
    }

    // Getters y Setters
    public Paciente getPacientes() {
        return pacientes;
    }

    public void setPacientes(Paciente pacientes) {
        this.pacientes = pacientes;
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

