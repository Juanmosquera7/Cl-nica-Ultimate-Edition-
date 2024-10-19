package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import java.util.List;

public class CancelarCitaControlador {

    @FXML
    private ComboBox<Cita> comboCitas;
    @FXML
    private Button btnEliminarCita;

    @FXML
    public void initialize() {
        cargarCitas(); // Cargar las citas al iniciar
    }

    private void cargarCitas() {
        comboCitas.getItems().clear(); //
        List<Cita> citas = Clinica.getInstance().getCitas(); //
        comboCitas.getItems().addAll(citas); //
    }

    @FXML
    public void eliminarCita(ActionEvent event) {
        try {
            Cita citaSeleccionada = comboCitas.getValue();
            if (citaSeleccionada == null) {
                mostrarAlerta("Por favor, selecciona una cita para eliminar.", Alert.AlertType.WARNING);
                return;
            }

            Clinica.getInstance().cancelarCita(citaSeleccionada);
            mostrarAlerta("Cita eliminada exitosamente.", Alert.AlertType.INFORMATION);
            cargarCitas();
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
}

