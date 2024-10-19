package co.edu.uniquindio.clinica.modelo;

import java.time.LocalDateTime;

public class Factura {

    private String id;
    private LocalDateTime fecha;
    private double subtotal;
    private double total;

    public Factura(String id, LocalDateTime fecha, double subtotal, double total) {
        this.id = id;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.total = total;
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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}

