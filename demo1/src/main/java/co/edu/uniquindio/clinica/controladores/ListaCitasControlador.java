package co.edu.uniquindio.clinica.controladores;

import co.edu.uniquindio.clinica.modelo.Cita;
import co.edu.uniquindio.clinica.modelo.Clinica;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.print.DocFlavor;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
public class ListaCitasControlador implements Initializable {
    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, String> colFecha;

    @FXML
    private TableColumn<Cita, String> colHora;

    @FXML
    private TableColumn<Cita, String> colPaciente;

    @FXML
    private TableColumn<Cita, String> colServicio;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        colFecha.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getFecha().format(dateFormatter)));
        colHora.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getFecha().format(timeFormatter)));
        colPaciente.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getPaciente().getNombre()));
        colServicio.setCellValueFactory(celda ->
                new SimpleStringProperty(celda.getValue().getServicio().getNombre()));


        tablaCitas.setItems(FXCollections.observableArrayList(Clinica.getInstance().getCitas()));
    }
}
