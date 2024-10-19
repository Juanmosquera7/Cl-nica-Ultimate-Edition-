package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.factory.Suscripcion;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionBasica;
import co.edu.uniquindio.clinica.modelo.factory.SuscripcionPremium;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class RegistroPacienteControlador{
    @FXML
    private TextField campoIdentificacion;
    @FXML
    private TextField campoNombre;
    @FXML

    private TextField campoTelefono;
    @FXML
    private TextField campoCorreo;
    @FXML
    private ComboBox<String> comboSuscripcion;

    private ListaPacientesControlador listaPacientesControlador;

    public void setListaPacientesControlador(ListaPacientesControlador listaPacientesControlador){
        this.listaPacientesControlador = listaPacientesControlador;
    }

    @FXML
    private Button btnRegistrar;

    @FXML
    public void registrarPaciente(ActionEvent event) {

        try {
            String cedula = campoIdentificacion.getText();
            String nombre = campoNombre.getText();
            String telefono = campoTelefono.getText();
            String email = campoCorreo.getText();


            Suscripcion suscripcion = obtenerSuscripcion();
            if (suscripcion == null) {
                mostrarAlerta("Por favor, selecciona un tipo de suscripción.", Alert.AlertType.WARNING);
                return;
            }

            // Agregar paciente a la clínica
            Clinica.getInstance().agregarPaciente(cedula, nombre, telefono, email, suscripcion);


            mostrarAlerta("Paciente registrado exitosamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Muestra una alerta en pantalla
     * @param mensaje Mensaje a mostrar
     * @param tipo Tipo de alerta
     */
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    private void limpiarCampos() {
        campoIdentificacion.clear();
        campoNombre.clear();
        campoTelefono.clear();
        campoCorreo.clear();
        comboSuscripcion.setValue(null);
    }

    private Suscripcion obtenerSuscripcion() {
        String tipoSuscripcion = comboSuscripcion.getValue();

        if ("Premium".equals(tipoSuscripcion)) {
            return new SuscripcionPremium();
        } else if ("Normal".equals(tipoSuscripcion)) {
            return new SuscripcionBasica();
        } else {
            return null;
        }
    }
}

