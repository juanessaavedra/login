package com.example.ejemplocrud;


import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import peliculas.Peliculas;

import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;


public class MainController implements Initializable { //Heredar las propiedades de la interfaz

    @FXML
    private TableView<Peliculas> TableView;

    @FXML
    private TableColumn<Peliculas, String> clasificacionColumn; //Clase y el tipo de dato que va devolver

    @FXML
    private TextField clasificacionFile; //Propiedades del JAVAFX

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Peliculas, String> fechaColumn;

    @FXML
    private TextField fechaField;

    @FXML
    private TableColumn<Peliculas, String> generoColumn;

    @FXML
    private TextField generoField;
    @FXML
    private TableColumn<Peliculas, Integer> idColumn;

    @FXML
    private TextField idField;

    @FXML
    private Button insertButton;

    @FXML
    private TableColumn<Peliculas, String> protagonistasColumn;

    @FXML
    private TextField protagonistasField;

    @FXML
    private TableColumn<Peliculas, String> titleColumn;

    @FXML
    private TextField titleField;

    @FXML
    private Button updateButton;

    @FXML
    private void insertButton() {
        String query = "INSERT INTO peliculas VALUES(" +
                idField.getText() + ",'" +
                titleField.getText() + "','" +
                protagonistasField.getText() + "','" +
                generoField.getText() + "','" +
                clasificacionFile.getText() + "','" +
                fechaField.getText() + "')";
        executeQuery(query);
        mostrarPeliculas();
    }

    @FXML
    private void updateButton() {
        String query = "UPDATE peliculas SET " +
                "titulo='" + titleField.getText() + "'," +
                "protagonistas='" + protagonistasField.getText() + "'," +
                "genero='" + generoField.getText() + "'," +
                "clasificacion='" + clasificacionFile.getText() + "'," +
                "fecha='" + fechaField.getText() + "' " +
                "WHERE ID=" + idField.getText();
        executeQuery(query);
        mostrarPeliculas();
    }

    @FXML
    private void deleteButton() {
        String query = "DELETE FROM peliculas WHERE ID=" + idField.getText();
        executeQuery(query);
        mostrarPeliculas();
    }




    public void executeQuery(String query) { //Base de datos, primero se pone porque es lo mas primordial
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mostrarPeliculas();
    } //Toddo lo que se va a inicializar apenas se ejecute el programa

    public Connection getConnection() { //Es lo primero que se hace para conectarla a la BD
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Este es igual para todos
            String url = "jdbc:mysql://localhost:3306/peliculasbd"; // Nombre de la BD y todod el link es igual, solo cambia el nombre de la BD, localhost: ubicacion de la BD, el 3306 es el puerto de la conexion.
            String username = "root"; //Si no lo hemos cambiado, el usuario general es este
            String password="2703"; //Mirar en la BD si tiene o no contraseña


            //phpyMyAdmin es donde administrar las BD en la web, crear nueva y creas tu BD, la BD se llama peliculasbd y la tabla se llama peliculas.

            //

            conn = DriverManager.getConnection(url, username,password);
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Peliculas> getPeliculaList(){
        ObservableList<Peliculas> peliculasList = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM peliculas ";
        Statement st;
        ResultSet rs;

        try {
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Peliculas peliculas;
            while(rs.next()) {
                peliculas = new Peliculas(rs.getInt("id"),rs.getString("titulo"),rs.getString("protagonistas"),rs.getString("genero"),rs.getString("clasificacion"),rs.getString("fecha"));
                peliculasList.add(peliculas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peliculasList;
    }

    public void mostrarPeliculas() {
        ObservableList<Peliculas> list = getPeliculaList();

        idColumn.setCellValueFactory(new PropertyValueFactory<Peliculas,Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Peliculas,String>("titulo"));
        protagonistasColumn.setCellValueFactory(new PropertyValueFactory<Peliculas,String>("protagonistas"));
        generoColumn.setCellValueFactory(new PropertyValueFactory<Peliculas,String>("genero"));
        clasificacionColumn.setCellValueFactory(new PropertyValueFactory<Peliculas,String>("clasificacion"));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<Peliculas, String>("fecha"));

        TableView.setItems(list);
    }

}
