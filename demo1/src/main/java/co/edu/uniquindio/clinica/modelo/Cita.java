package co.edu.uniquindio.clinica.modelo;

import java.time.LocalDateTime;

public class Cita {

    private String id;
    private LocalDateTime fecha;
    private Paciente paciente;
    private Servicio servicio;
    private Factura factura;

    public Cita(String id, LocalDateTime fecha, Paciente paciente, Servicio servicio, Factura factura) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.servicio = servicio;
        this.factura = factura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id='" + id + '\'' +
                ", fecha=" + fecha +
                ", paciente=" + paciente.getNombre() +
                '}';
    }


}

