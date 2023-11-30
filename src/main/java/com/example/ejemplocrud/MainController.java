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


public class MainController implements Initializable {

    @FXML
    private TableView<Peliculas> TableView;

    @FXML
    private TableColumn<Peliculas, String> clasificacionColumn;

    @FXML
    private TextField clasificacionFile;

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




    public void executeQuery(String query) {
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
    }

    public Connection getConnection() {
        Connection conn;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/peliculasbd";
            String username = "root";
            String password="2703";

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
