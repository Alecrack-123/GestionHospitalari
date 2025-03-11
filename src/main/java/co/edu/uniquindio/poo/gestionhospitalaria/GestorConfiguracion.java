package co.edu.uniquindio.poo.gestionhospitalaria;

public class GestorConfiguracion {
    private static GestorConfiguracion instance;
    private String horarioAtencion;
    private int numeroMaxPacientes;
    private String reglasFacturacion;

    private GestorConfiguracion(String horarioAtencion, int numeroMaxPacientes, String reglasFacturacion) {
        this.horarioAtencion = horarioAtencion;
        this.numeroMaxPacientes = numeroMaxPacientes;
        this.reglasFacturacion = reglasFacturacion;
    }

    // Inicializar la instancia por primera vez
    public static void inicializar(String horarioAtencion, int numeroMaxPacientes, String reglasFacturacion) {
        if (instance == null) {
            instance = new GestorConfiguracion(horarioAtencion, numeroMaxPacientes, reglasFacturacion);
        }
    }

    // Obtener la instancia ya creada
    public static GestorConfiguracion getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GestorConfiguracion no ha sido inicializado.");
        }
        return instance;
    }

    // Getters y setters
    public String getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(String horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public int getNumeroMaxPacientes() {
        return numeroMaxPacientes;
    }

    public void setNumeroMaxPacientes(int numeroMaxPacientes) {
        this.numeroMaxPacientes = numeroMaxPacientes;
    }

    public String getReglasFacturacion() {
        return reglasFacturacion;
    }

    public void setReglasFacturacion(String reglasFacturacion) {
        this.reglasFacturacion = reglasFacturacion;
    }
}

