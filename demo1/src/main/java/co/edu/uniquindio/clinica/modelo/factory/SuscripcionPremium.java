package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class SuscripcionPremium implements Suscripcion{
    @Override
    public List<Servicio> listaServicios() {
        return List.of(
                new Servicio("1", "Consulta General", 0),
                new Servicio("2", "Odontología", 100),
                new Servicio("3", "Oftalmología", 150)
        );
    }

    @Override
    public Factura generarFactura(Servicio servicio) {
        double subtotal = servicio.getPrecio();
        double total = subtotal;
        String nombreServicio = servicio.getNombre();

        // Aplicar descuentos según el servicio
        if ("Odontología".equals(nombreServicio)) {
            total = 0; // Servicio gratuito
        } else if ("Cirugía".equals(nombreServicio)) {
            total = subtotal * 0.50; // 50% de descuento
        } else if ("Consulta General".equals(nombreServicio)) {
            total = 0; // Servicio gratuito
        }

        // Crear la factura con el subtotal y total calculado
        return new Factura(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                subtotal,
                total
        );
    }
}
