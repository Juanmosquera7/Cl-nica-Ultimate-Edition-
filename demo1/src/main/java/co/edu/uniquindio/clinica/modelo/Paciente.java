package co.edu.uniquindio.clinica.modelo;

import lombok.Builder;
import lombok.Data;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;

@Data // Genera getters, setters y otros métodos útiles
@Builder // Permite el uso del patrón Builder
public class Paciente {

    private String cedula;
    private String nombre;
    private String telefono;
    private String email;
    private Suscripcion suscripcion;
}


