package co.edu.uniquindio.poo.gestionhospitalaria;

import java.util.ArrayList;
import java.util.List;

public class Medico{

private String nombre;
private String especialidad;
private int maxPacientes;


public Medico(String nombre, String especialidad, int maxPacientes) {
    this.nombre = nombre;
    this.especialidad = especialidad;
    this.maxPacientes = maxPacientes;

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


    @Override
    public String toString() {
        return nombre;
    }

}

