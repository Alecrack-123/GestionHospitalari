package co.edu.uniquindio.poo.gestionhospitalaria;

public class Paciente extends Persona implements Cloneable {
    private String historialEnfermedades;
    private String medicamentos;


    public Paciente(String nombre, int edad, String historialEnfermedades, String medicamentos) {
        super(nombre, edad);
        this.historialEnfermedades = historialEnfermedades;
        this.medicamentos = medicamentos;
    }

    public String getHistorialEnfermedades() {
        return historialEnfermedades;
    }

    public void setHistorialEnfermedades(String historialEnfermedades) {
        this.historialEnfermedades = historialEnfermedades;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public Paciente clone() {
        try {
            return (Paciente) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar el paciente", e);
        }
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "historialEnfermedades='" + historialEnfermedades + '\'' +
                ", medicamentos='" + medicamentos + '\'' +
                '}';
    }
}

