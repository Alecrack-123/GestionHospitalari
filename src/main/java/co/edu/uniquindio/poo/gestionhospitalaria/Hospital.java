package co.edu.uniquindio.poo.gestionhospitalaria;

import java.util.List;
import java.util.ArrayList;

public class Hospital {
    private String nombre;
    private List<Medico> medicos;
    private List<Paciente> pacientes;

    public Hospital(String nombre) {
        this.nombre = nombre;
        this.medicos = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public void agregarMedico(Medico medico) {
        medicos.add(medico);
    }

    public void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }
}
