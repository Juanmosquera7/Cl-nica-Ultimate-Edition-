package co.edu.uniquindio.clinica.modelo.factory;

import co.edu.uniquindio.clinica.modelo.Servicio;

import co.edu.uniquindio.clinica.modelo.Factura;

import java.util.List;

public interface Suscripcion {

    List<Servicio> listaServicios();

    Factura generarFactura(Servicio servicio);
}
