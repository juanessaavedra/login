package peliculas;

import javafx.beans.property.ObjectProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Peliculas {
    private Integer id;
    private String titulo;
    private String protagonistas;
    private String genero;
    private String clasificacion;
    private String fecha;


    public Peliculas(Integer id, String titulo, String protagonistas, String genero, String clasificacion, String fecha) {
        this.id = id;
        this.titulo = titulo;
        this.protagonistas = protagonistas;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getProtagonistas() {
        return protagonistas;
    }

    public String getGenero() {
        return genero;
    }


    public String getClasificacion() {
        return clasificacion;
    }


    public String getFecha() {
        return fecha;
    }

}
