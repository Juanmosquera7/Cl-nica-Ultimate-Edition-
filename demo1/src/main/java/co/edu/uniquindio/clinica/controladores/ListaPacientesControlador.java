package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Clinica;
import co.edu.uniquindio.clinica.modelo.Paciente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaPacientesControlador implements Initializable {

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, String> colIdentificacion;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, String> colTelefono;

    @FXML
    private TableColumn<Paciente, String> colCorreo;

    @FXML
    private TableColumn<Paciente, String> colSuscripcion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colIdentificacion.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getCedula()));
        colNombre.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getNombre()));
        colTelefono.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getTelefono()));
        colCorreo.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getEmail()));
        colSuscripcion.setCellValueFactory(celda -> new SimpleStringProperty(celda.getValue().getSuscripcion().getClass().getSimpleName()));


        tablaPacientes.setItems(FXCollections.observableArrayList(Clinica.getInstance().getPacientes()));
    }
}
