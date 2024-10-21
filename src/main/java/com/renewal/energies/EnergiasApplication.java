package com.renewal.energies;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EnergiasApplication extends Application {
    @Override
    public void start ( Stage stage ) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader( EnergiasApplication.class.getResource( "energias.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load(), 800, 800 );
        stage.setTitle( "Energias Renovables" );
        stage.setScene( scene );
        stage.show();
    }
    
    public static void main ( String[] args ) {
        launch();
    }
}