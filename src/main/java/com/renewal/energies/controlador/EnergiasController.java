package com.renewal.energies.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

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
        // Cargar la imagen del ícono
//        URL guardarIcon = getClass().getResource( "/guardar.png" );
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
}
