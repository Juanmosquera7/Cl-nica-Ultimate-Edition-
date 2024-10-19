package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Factura;
import co.edu.uniquindio.clinica.modelo.Servicio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import java.util.List;

public class SuscripcionBasica implements Suscripcion{
    @Override
    public List<Servicio> listaServicios() {
        return List.of(
                new Servicio("1", "Consulta General", 0),
                new Servicio("2", "Odontología", 100),
                new Servicio("3", "Cirugía", 500)
        );
    }

    @Override
    public Factura generarFactura(Servicio servicio) {
        double subtotal = servicio.getPrecio();
        double total = subtotal;
        String nombreServicio = servicio.getNombre();

        // Aplicar descuentos según el servicio
        if ("Odontología".equals(nombreServicio)) {
            total = subtotal * 0.80; // 20% de descuento
        } else if ("Oftalmología".equals(nombreServicio)) {
            total = subtotal * 0.85; // 15% de descuento
        } else if ("Consulta General".equals(nombreServicio)) {
            total = 0; // Servicio gratuito
        }

        // Crear la factura con el subtotal y total calculado
        return new Factura(
                UUID.randomUUID().toString(), // Generar un ID único para la factura
                LocalDateTime.now(),
                subtotal,
                total
        );
    }
}
