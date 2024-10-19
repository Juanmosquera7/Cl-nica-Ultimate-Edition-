package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import co.edu.uniquindio.clinica.modelo.Servicio;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CrearCitaControlador {
    @FXML
    private ComboBox<Paciente> comboPacientes;
    @FXML
    private ComboBox<Servicio> comboServicios;
    @FXML
    private DatePicker campoFecha;
    @FXML
    private ComboBox<LocalTime> comboHora;
    @FXML
    private Button btnCrearCita;

    @FXML
    public void initialize() {
        cargarPacientes();
        cargarHoras();
    }

    private void cargarPacientes() {
        comboPacientes.getItems().clear();
        List<Paciente> pacientes = Clinica.getInstance().getPacientes();
        comboPacientes.getItems().addAll(pacientes);
    }

    private void cargarHoras() {
        List<LocalTime> horas = new ArrayList<>();
        for (int i = 8; i < 18; i++) { // De 8 AM a 5 PM
            horas.add(LocalTime.of(i, 0));
            horas.add(LocalTime.of(i, 30));
        }
        comboHora.getItems().addAll(horas);
    }

    @FXML
    private void manejarPacienteSeleccionado() {
        Paciente pacienteSeleccionado = comboPacientes.getValue();
        if (pacienteSeleccionado != null) {
            cargarServiciosSegunSuscripcion(pacienteSeleccionado); // Carga servicios según suscripción
        }
    }

    private void cargarServiciosSegunSuscripcion(Paciente paciente) {
        comboServicios.getItems().clear();
        Suscripcion suscripcion = paciente.getSuscripcion();
        List<Servicio> servicios = suscripcion.listaServicios();
        comboServicios.getItems().addAll(servicios);
    }

    @FXML
    public void crearCita(ActionEvent event) {
        try {
            Paciente pacienteSeleccionado = comboPacientes.getValue();
            Servicio servicioSeleccionado = comboServicios.getValue();
            LocalDateTime fechaSeleccionada = campoFecha.getValue().atTime(comboHora.getValue()); // Usa la hora seleccionada

            // Verificar que la fecha seleccionada no sea anterior a la fecha actual
            if (fechaSeleccionada.isBefore(LocalDateTime.now())) {
                mostrarAlerta("La fecha y hora de la cita no pueden ser anteriores a la fecha y hora actuales.", Alert.AlertType.WARNING);
                return;
            }

            if (pacienteSeleccionado == null || servicioSeleccionado == null || fechaSeleccionada == null) {
                mostrarAlerta("Por favor, selecciona un paciente, un servicio, una fecha y una hora.", Alert.AlertType.WARNING);
                return;
            }

            // Genera un ID para la cita, puedes implementar un método para esto
            String idCita = String.valueOf(System.currentTimeMillis());

            // Crear la cita en la clínica
            Clinica.getInstance().agendarCita(idCita, pacienteSeleccionado, servicioSeleccionado, fechaSeleccionada);

            mostrarAlerta("Cita creada exitosamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }


    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        comboPacientes.setValue(null);
        comboServicios.getItems().clear();
        campoFecha.setValue(null);
        comboHora.setValue(null);
    }
}



