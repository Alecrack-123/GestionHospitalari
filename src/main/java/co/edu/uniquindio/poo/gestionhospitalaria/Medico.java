package co.edu.uniquindio.poo.gestionhospitalaria;

import java.util.ArrayList;
import java.util.List;

public class Medico{

private String nombre;
private String especialidad;
private int maxPacientes;
private List<Paciente> pacientesAsignados;

public Medico(String nombre, String especialidad, int maxPacientes) {
    this.nombre = nombre;
    this.especialidad = especialidad;
    this.maxPacientes = maxPacientes;
    this.pacientesAsignados = new ArrayList<>();
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getEspecialidad() {
    return especialidad;
}

public void setEspecialidad(String especialidad) {
    this.especialidad = especialidad;
}

public int getMaxPacientes() {
    return maxPacientes;
}

public void setMaxPacientes(int maxPacientes) {
    this.maxPacientes = maxPacientes;
}

public List<Paciente> getPacientesAsignados() {
    return pacientesAsignados;
}

    @Override
    public String toString() {
        return "Medico{" +
                "nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", maxPacientes=" + maxPacientes +
                ", pacientesAsignados=" + pacientesAsignados +
                '}';
    }

    public boolean asignarPaciente(Paciente paciente) {
    if (pacientesAsignados.size() < maxPacientes) {
        pacientesAsignados.add(paciente);
        return true;
    } else {
        System.out.println("El doctor " + nombre + " ha alcanzado el número máximo de pacientes.");
        return false;
    }

}
}

