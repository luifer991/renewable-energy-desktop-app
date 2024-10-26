package com.renewal.energies.controlador;

import com.renewal.energies.clases.ResultadoBusqueda;
import com.renewal.energies.modelo.EnergiaSolarDAO;
import com.renewal.energies.util.ConeccionBD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;

public class EnergiasController {
    @FXML
    private Button btnGuardar, btnLimpiar, btnActualizar, btnEliminar, btnBuscar;
    @FXML
    private TableView <ResultadoBusqueda> tblTabla;
    @FXML
    private TableColumn <ResultadoBusqueda, Integer> clId;
    @FXML
    TableColumn <ResultadoBusqueda, BigDecimal> clCapacidad, clEficiencia,
            clRadiacion, clArea, clAngulo;
    @FXML
    TableColumn <ResultadoBusqueda, String> clCodigo, clTipo, clUbicacion;
    @FXML
    TableColumn <ResultadoBusqueda, Date> clFecha;
    
    private EnergiaSolarDAO energiaSolarDAO;
    
    @FXML
    public void initialize () throws SQLException {
        agregarIcono();
        energiaSolarDAO = new EnergiaSolarDAO();
        
        clId.setCellValueFactory( new PropertyValueFactory <>( "id" ) );
        clCodigo.setCellValueFactory( new PropertyValueFactory <>( "codigo" ) );
        clUbicacion.setCellValueFactory( new PropertyValueFactory <>( "ubicacion" ) );
        clCapacidad.setCellValueFactory( new PropertyValueFactory <>( "capacidadinstalada" ) );
        clEficiencia.setCellValueFactory( new PropertyValueFactory <>( "eficiencia" ) );
        clTipo.setCellValueFactory( new PropertyValueFactory <>( "tipoenergia" ) );
        clRadiacion.setCellValueFactory( new PropertyValueFactory <>( "radiacionsolar" ) );
        clArea.setCellValueFactory( new PropertyValueFactory <>( "areapaneles" ) );
        clAngulo.setCellValueFactory( new PropertyValueFactory <>( "anguloinclinacion" ) );
        clFecha.setCellValueFactory( new PropertyValueFactory <>( "fechacreacion" ) );
        // Asignar el evento de clic al botón Guardar
        btnGuardar.setOnAction( e -> mostrarDialogoGuardar() );
        mostrarDatos();
    }
    
    
    public void mostrarDatos() throws SQLException {
        tblTabla.setItems( energiaSolarDAO.obtenerDatos() );
    }
    
    private void mostrarDialogoGuardar () {
        Alert dialogo = new Alert( Alert.AlertType.CONFIRMATION );
        dialogo.setTitle( "Crear" );
        dialogo.setHeaderText( "¿Deseas continuar para llenar el formulario?" );
        dialogo.setContentText( "Haz clic en el tipo de energía renovable el cual desees agregar datos." );
        
        // Configurar botones
        ButtonType btnEnergiasSolar = new ButtonType( "Energía Solar", ButtonBar.ButtonData.OK_DONE );
        ButtonType btnEnergiasEolica = new ButtonType( "Energía Eólica", ButtonBar.ButtonData.OK_DONE );
        ButtonType btnEnergiasGeotermica = new ButtonType( "Energía Geotérmica", ButtonBar.ButtonData.OK_DONE );
        ButtonType btnEnergiasHidraulica = new ButtonType( "Energía Hidraulica", ButtonBar.ButtonData.OK_DONE );
        ButtonType btnBiomasa = new ButtonType( "Biomasa", ButtonBar.ButtonData.OK_DONE );
        
        dialogo.getButtonTypes().setAll(
                btnEnergiasSolar, btnEnergiasEolica,
                btnEnergiasGeotermica, btnEnergiasHidraulica, btnBiomasa
        );
        
        dialogo.showAndWait().ifPresent( response -> {
            if ( response == btnEnergiasSolar ) {
                // Si se presiona "Aceptar", abrir nueva ventana con el formulario
                abrirFormulario();
            }
        } );
    }
    
    private void abrirFormulario () {
        try {
            URL fxmlLocation = getClass().getResource( "/com/renewal/energies/energiasolar.fxml" );
            if ( fxmlLocation == null ) {
                System.out.println( "No se encontró el archivo energiasolar.fxml" );
                return; // Salir si el archivo no se encuentra
            }
            FXMLLoader loader = new FXMLLoader( fxmlLocation );
            Stage stage = new Stage();
            stage.initModality( Modality.APPLICATION_MODAL ); // Hacer que la nueva ventana sea modal
            stage.setTitle( "Formulario de Registro" );
            stage.setScene( new Scene( loader.load(), 800, 600 ) );
            stage.show();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
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
        // covertir a imagen
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
