package com.renewal.energies.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;

public class EnergiasController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button btnGuardar;
    
    @FXML
    public void initialize() {
        agregarIcono();
    }
    
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    
    private void agregarIcono() {
        // Cargar la imagen del ícono
        URL guardarIcon = getClass().getResource("/guardar.png");
        if (guardarIcon == null) {
            System.out.println("No se pudo encontrar el recurso");
            return;
        }
        
        // Crear la imagen con un tamaño más visible
        Image icono = new Image(guardarIcon.toExternalForm());
        
        // Crear el ImageView con la imagen y establecer su tamaño
        ImageView imageView = new ImageView(icono);
        imageView.setFitWidth(20);  // Aumentamos el tamaño a 30x30
        imageView.setFitHeight(20);
        imageView.setPreserveRatio(true);  // Mantener la proporción original del ícono
        
        // Asignar el ImageView al botón
        btnGuardar.setGraphic(imageView);
    }
}
