package co.edu.uniquindio.clinica.modelo;

import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.utils.EnvioEmail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Clinica {

    private List<Cita> citas;
    private List<Servicio> servicios;
    private List<Paciente> pacientes;
    private static Clinica INSTANCIA;

    private Clinica() {
        this.citas = new ArrayList<>();
        this.servicios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public static Clinica getInstance(){
        if(INSTANCIA == null){
            INSTANCIA = new Clinica();
            return INSTANCIA;
        }else {
            return INSTANCIA;
        }
    }

    public Clinica(List<Cita> citas, List<Servicio> servicios, List<Paciente> pacientes) {
        this.citas = citas;
        this.servicios = servicios;
        this.pacientes = pacientes;
    }

    // Método para agregar un paciente
    public void agregarPaciente(String cedula, String nombre, String telefono, String email, Suscripcion suscripcion) throws Exception {
        if (cedula.isEmpty() || nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            throw new Exception("Todos los campos son obligatorios");
        }


        if (!cedula.matches("\\d+")) { // Verifica que la cédula solo contenga dígitos
            throw new Exception("La cédula debe contener solo números.");
        }

        if (!telefono.matches("\\d+")) { // Verifica que el teléfono solo contenga dígitos
            throw new Exception("El teléfono debe contener solo números.");
        }


        Paciente paciente = Paciente.builder()
                .cedula(cedula)
                .nombre(nombre)
                .telefono(telefono)
                .email(email)
                .suscripcion(suscripcion)
                .build();

        if (existePacienteConDatos(paciente)) {
            throw new Exception("Ya existe un paciente con la misma cédula, nombre, teléfono o correo electrónico.");
        }

        pacientes.add(paciente);




    }

    public void agendarCita(String id, Paciente paciente, Servicio servicio, LocalDateTime fechaHora) throws Exception {

        if (!pacientes.contains(paciente)) {
            throw new Exception("El paciente no está registrado.");
        }


        if (existeConflictoDeHorario(fechaHora)) {
            throw new Exception("Ya existe una cita agendada en ese horario.");
        }








        Factura factura = paciente.getSuscripcion().generarFactura(servicio);


        Cita nuevaCita = new Cita(id, fechaHora, paciente, servicio, factura);


        citas.add(nuevaCita);


        enviarEmailConfirmacion(nuevaCita);
    }


    private boolean existeConflictoDeHorario(LocalDateTime fechaHora) {
        for (Cita cita : citas) {
            if (cita.getFecha().equals(fechaHora)) {
                return true;
            }
        }
        return false;
    }

    private boolean existePacienteConDatos(Paciente paciente) {
        for (Paciente p : pacientes) {
            if (p.getCedula().equals(paciente.getCedula()) ||
                    p.getTelefono().equals(paciente.getTelefono()) ||
                    p.getEmail().equalsIgnoreCase(paciente.getEmail()) ||
                    p.getNombre().equalsIgnoreCase(paciente.getNombre())) {
                return true;
            }
        }
        return false;
    }



    private void enviarEmailConfirmacion(Cita cita) {
        String destinatario = cita.getPaciente().getEmail();
        String asunto = "Confirmación de Cita";
        String mensaje = "Detalles de la cita:\n" +
                "Servicio: " + cita.getServicio().getNombre() + "\n" +
                "Fecha y Hora: " + cita.getFecha() + "\n" +
                "Factura:\n" +
                "Subtotal: " + cita.getFactura().getSubtotal() + "\n" +
                "Total: " + cita.getFactura().getTotal();

        EnvioEmail.enviarNotificacion(destinatario, asunto, mensaje);
    }

    private void enviarEmailCancelacion(Cita cita) {
        String destinatario = cita.getPaciente().getEmail();
        String asunto = "Cancelación de Cita";
        String mensaje = "Lamentamos informarle que su cita con los siguientes detalles ha sido cancelada:\n" +
                "Servicio: " + cita.getServicio().getNombre() + "\n" +
                "Fecha y Hora: " + cita.getFecha();

        EnvioEmail.enviarNotificacion(destinatario, asunto, mensaje);
    }


    public Paciente buscarPacientePorNombre(String nombre) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNombre().equalsIgnoreCase(nombre)) {
                return paciente;
            }
        }
        return null;
    }

    public Servicio buscarServicioPorNombre(String nombre) {
        for (Servicio servicio : servicios) {
            if (servicio.getNombre().equalsIgnoreCase(nombre)) {
                return servicio;
            }
        }
        return null;
    }
    public Suscripcion obtenerSuscripcionPorPaciente(String cedula) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCedula().equals(cedula)) {
                return paciente.getSuscripcion();
            }
        }
        return null;
    }

    public void cancelarCita(Cita cita) throws Exception {
        if (!citas.contains(cita)) {
            throw new Exception("La cita no existe.");
        }
        citas.remove(cita);
        enviarEmailCancelacion(cita);
    }





    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }


}

