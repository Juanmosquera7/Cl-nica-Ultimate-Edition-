package co.edu.uniquindio.clinica.modelo;

public class Servicio {

    private String id;
    private String nombre;
    private float precio;



    public Servicio(String id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    // Sobrescribiendo el método toString
    @Override
    public String toString() {
        return nombre; // Devolvemos el nombre del servicio para que se muestre en el ComboBox
    }
}
