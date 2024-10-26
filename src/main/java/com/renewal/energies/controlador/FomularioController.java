package com.renewal.energies.controlador;

import com.renewal.energies.modelo.EnergiaSolarDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class FomularioController {
    
    @FXML
    private TextField txtRadiacion, txtAreaPaneles, txtInclinacion,
            txtCodigo, txtUbicacion, txtCapacidadInstalada,
            txtEficiencia, txtFechaCreacion, txtPais,
            txtEnergiaRequerida, txtCovertura, txtPoblacion,
            txtTipoEnergia, txtPlantaProducionId;
    @FXML
    private Button btnGuardar, btnCancelar;
    @FXML
    private Label lblBase;
    
    private EnergiaSolarDAO energiaSolarDAO;
    
    @FXML
    public void initialize () {
        energiaSolarDAO = new EnergiaSolarDAO();
        btnGuardar.setOnAction( t -> extraerDatos() );
        // cargarAreaPaneles();
    }
    
    // Método para cargar el área de paneles y mostrarla en el TextField
    private void cargarAreaPaneles () {
        Double areaPaneles = energiaSolarDAO.obtenerInformacionDeBaseDeDatos();
        txtAreaPaneles.setText( areaPaneles.toString() ); // Muestra el resultado en el TextField
    }
    
    @FXML
    public void extraerDatos () {
        try {
            BigDecimal rad = BigDecimal.valueOf( Double.parseDouble( txtRadiacion.getText() ) );
            BigDecimal area = BigDecimal.valueOf( Double.parseDouble( txtAreaPaneles.getText() ) );
            BigDecimal inc = BigDecimal.valueOf( Double.parseDouble( txtInclinacion.getText() ) );
            String cod = txtCodigo.getText();
            String ubi = txtUbicacion.getText();
            BigDecimal cap = BigDecimal.valueOf( Double.parseDouble( txtCapacidadInstalada.getText() ) );
            BigDecimal eficiencia = BigDecimal.valueOf( Double.parseDouble( txtEficiencia.getText() ) );
            Date fecha = Date.valueOf( txtFechaCreacion.getText() );
            String pais = txtPais.getText();
            BigDecimal energia = BigDecimal.valueOf( Double.parseDouble( txtEnergiaRequerida.getText() ) );
            BigDecimal covertura = BigDecimal.valueOf( Double.parseDouble( txtCovertura.getText() ) );
            BigDecimal poblacion = BigDecimal.valueOf( Double.parseDouble( txtPoblacion.getText() ) );
            int tipoEnergia = Integer.parseInt( txtTipoEnergia.getText() );
            int plantaProduccionId = Integer.parseInt( txtPlantaProducionId.getText() );
            System.out.println( "Radiación: " + rad +
                    " Area de los Paneles (km2): " + area
                    + " Inclinacion (°): " + inc +
                    " Codigo: " + cod +
                    " Ubicacion: " + ubi + " Capacidad Instalada (kW): " + cap +
                    " Eficiencia (%): " + eficiencia +
                    " Fecha de Creacion: " + fecha +
                    " Pais: " + pais +
                    " Energia Requerida (kWh): " + energia +
                    " Covertura: " + covertura +
                    " Poblacion: " + poblacion +
                    " Tipo de Energia: " + tipoEnergia +
                    " Planta de Produccion ID: " + plantaProduccionId );
            mostrarMensaje( "Exito", "Datos extraidos correctamente" );
            energiaSolarDAO.guardar( rad, area, inc, cod, tipoEnergia, ubi, cap, eficiencia, fecha );
            energiaSolarDAO.guardarPais( pais, energia, covertura, poblacion, plantaProduccionId );
            limpiarCampos();
        } catch ( DateTimeParseException e ) {
            mostrarError( "Error", "El formato de fecha es incorrecto. Utiliza el formato 'yyyy-MM-dd'." );
        } catch ( SQLException e ) {
            mostrarError( "Error en la base de datos", e.getMessage() );
        } catch ( Exception e ) {
            mostrarError( "Error", "Error al guardar los datos: " + e.getMessage() );
        }
    }
    
    // Método para mostrar un cuadro de diálogo de error
    private void mostrarError ( String titulo, String mensaje ) {
        Alert alerta = new Alert( Alert.AlertType.ERROR );
        alerta.setTitle( titulo );
        alerta.setHeaderText( null );
        alerta.setContentText( mensaje );
        alerta.showAndWait();
    }
    
    private void limpiarCampos () {
        txtRadiacion.clear();
        txtAreaPaneles.clear();
        txtInclinacion.clear();
        txtCodigo.clear();
        txtUbicacion.clear();
        txtCapacidadInstalada.clear();
        txtEficiencia.clear();
        txtFechaCreacion.clear();
        txtPais.clear();
        txtEnergiaRequerida.clear();
        txtCovertura.clear();
        txtPoblacion.clear();
        txtTipoEnergia.clear();
        txtPlantaProducionId.clear();
    }
    
    private void mostrarMensaje ( String titulo, String mensaje ) {
        Alert alerta = new Alert( Alert.AlertType.INFORMATION );
        alerta.setTitle( titulo );
        alerta.setHeaderText( null );
        alerta.setContentText( mensaje );
        alerta.showAndWait();
    }
}
