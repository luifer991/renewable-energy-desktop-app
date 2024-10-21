package com.renewal.energies.controlador;

import com.renewal.energies.util.ConeccionBD;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnergiasController {
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnBuscar;
    
    @FXML
    public void initialize () {
        agregarIcono();
    }
    
    //    @FXML
//    protected void onHelloButtonClick () {
//        welcomeText.setText( "Welcome to JavaFX Application!" );
//    }
//
    private void agregarIcono () {
        icono( "/guardar.png", btnGuardar );
        icono( "/buscar.png", btnBuscar );
        icono( "/limpiar.png", btnLimpiar );
        icono( "/actualizar.png", btnActualizar );
        icono( "/eliminar.png", btnEliminar );
    }
    
    public void icono ( String icon, Button btn ) {
        // obtener el icono
        URL getIcon = getClass().getResource( icon );
        // covertilo a imagen
        Image getImage = new Image( getIcon.toExternalForm() );
        // Crear el ImageView con la imagen y establecer su tamaño
        ImageView setIcon = new ImageView( getImage );
        setIcon.setFitWidth( 20 );  // Aumentamos el tamaño a 20x20
        setIcon.setFitHeight( 20 );
        setIcon.setPreserveRatio( true );  // Mantener la proporción original del ícono
        //asignar el icono al boton
        btn.setGraphic( setIcon );
    }
    
    @FXML
    protected void crearRegistrp () {
    
    }
    
    // Método para obtener la información de la base de datos
    private String obtenerInformacionDeBaseDeDatos () {
        String resultado = "";
        String query = "SELECT frase FROM frases ORDER BY RANDOM() LIMIT 1"; // Ajusta la consulta según tu necesidad
        
        try ( Connection conn = ConeccionBD.connect();
              PreparedStatement pstmt = conn.prepareStatement( query );
              ResultSet rs = pstmt.executeQuery() ) {
            
            // Si hay un resultado, obtenemos el nombre del cliente
            if ( rs.next() ) {
                resultado = rs.getString( "frase" );
            }
        } catch ( SQLException e ) {
            System.out.println( "Error en la consulta: " + e.getMessage() );
        }
        
        return resultado;
    }
    
}
