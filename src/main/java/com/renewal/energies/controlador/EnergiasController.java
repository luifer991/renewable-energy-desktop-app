package com.renewal.energies.controlador;

import com.renewal.energies.clases.ResultadoBusqueda;
import com.renewal.energies.modelo.EnergiaSolarDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnergiasController {
    
    @FXML
    private Button btnGuardar, btnLimpiar, btnActualizar, btnEliminar, btnBuscar, btnRecargar;
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
        
        btnActualizar.setOnAction( e -> actualizarDatosDialogo() );
        
        btnRecargar.setOnAction( e -> {
            try {
                actualizarTabla();
                mostrarDatos();
            } catch ( SQLException ex ) {
                throw new RuntimeException( ex );
            }
        } );
        
        btnBuscar.setOnAction( e -> mostrarDialogoBusqueda() );
        
        setValueToCella();
        // Asignar el evento de clic al botón Guardar
        btnGuardar.setOnAction( e -> mostrarDialogoGuardar() );
        mostrarDatos();
        
        btnEliminar.setOnAction( e -> mostrarDialogoEliminar() );
    }
    
    //Metodo para asignar el valor de las propieddes de la tabla que conectan con el servicio
    public void setValueToCella () {
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
    }
    
    // Metodo para obtener los datos actualizados y mostrar en la tabla
    private void actualizarTabla () throws SQLException {
        // Limpiar la lista actual
        energiaSolarDAO.obtenerDatos().clear();
        // Aquí obtienes los nuevos datos (por ejemplo, de la base de datos)
        ObservableList <ResultadoBusqueda> nuevosDatos = energiaSolarDAO.obtenerDatos();
        // Agregar los nuevos datos a la lista
        energiaSolarDAO.obtenerDatos().addAll( nuevosDatos );
        // Opcionalmente, puedes refrescar la tabla si es necesario
        tblTabla.refresh();
    }
    
    public void mostrarDatos () throws SQLException {
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
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        
        dialogo.getButtonTypes().setAll(
                btnEnergiasSolar, btnEnergiasEolica,
                btnEnergiasGeotermica, btnEnergiasHidraulica, btnBiomasa, btnCancelar
        );
        
        dialogo.showAndWait().ifPresent( response -> {
            if ( response == btnEnergiasSolar ) {
                // Si se presiona "Aceptar", abrir nueva ventana con el formulario
                abrirFormulario();
            }
        } );
    }
    
    public void mostrarDialogoEliminar () {
        Alert dialogo = new Alert( Alert.AlertType.CONFIRMATION );
        dialogo.setTitle( "Eliminar" );
        dialogo.setHeaderText( "¿Deseas continuar para eliminar el formulario?" );
        dialogo.setContentText( "Haz clic en el tipo de energía renovable el cual desees eliminar datos." );
        //configurar textFields
        TextField txtId = new TextField();
        txtId.setPromptText( "ID" );
        dialogo.getDialogPane().setContent( txtId );
        
        dialogo.getButtonTypes().setAll( ButtonType.OK, ButtonType.CANCEL );
        
        dialogo.showAndWait().ifPresent( response -> {
            if ( response == ButtonType.OK ) {
                // Si se presiona "Aceptar", abrir nueva ventana con el formulario
                try {
                    energiaSolarDAO.eliminarDatos( Integer.parseInt( txtId.getText() ) );
                } catch ( SQLException e ) {
                    throw new RuntimeException( e );
                }
                System.out.println("Datos eliminados con 'exito ");
            }
        } );
    }
    
    public TextField crearTextFields ( String promptText ) {
        TextField txt = new TextField();
        txt.setPromptText( promptText );
        
        return txt;
    }
    
    public void actualizarDatosDialogo () {
        Alert dialogo = new Alert( Alert.AlertType.CONFIRMATION );
        dialogo.setTitle( "Actualizar" );
        dialogo.setHeaderText( "¿Deseas Actualizar los datos?" );
        dialogo.setContentText( "Haz clic en el tipo de energía renovable el cual desees actualizar datos." );
        
        // Configurar TextFields
        var txtId = crearTextFields( "ID" );
        TextField txtCapacidad = crearTextFields( "capacidadIntalada" );
        TextField txtEficiencia = crearTextFields( "eficiencia" );
        TextField txtRadiacion = crearTextFields( "Radiacion" );
        TextField txtArea = crearTextFields( "area" );
        TextField txtAngulo = crearTextFields( "angulo" );
        TextField txtCodigo = crearTextFields( "Codigo" );
        int tipo = 5;
        //TextField txtTipo = crearTextFields( "tipo" );
        TextField txtUbicacion = crearTextFields( "ubicacion" );
        DatePicker txtFecha = new DatePicker();
        txtFecha.setPromptText( "fechacreacion" );
        TextField txtPais = crearTextFields( "pais" );
        TextField txtEnergia = crearTextFields( "cant. energia" );
        TextField txtCovertura = crearTextFields( "covertura" );
        TextField txtPoblacion = crearTextFields( "poblacion" );
//        TextField txtPlantaProduccionId = crearTextFields( "plantaProduccionID" );
        
        GridPane gridPane = new GridPane();
        gridPane.add( txtId, 0, 0 );
        gridPane.add( txtCapacidad, 0, 1 );
        gridPane.add( txtEficiencia, 0, 2 );
        gridPane.add( txtRadiacion, 0, 3 );
        gridPane.add( txtArea, 0, 4 );
        gridPane.add( txtAngulo, 0, 5 );
        gridPane.add( txtCodigo, 0, 6 );
//        gridPane.add( txtTipo, 0, 7 );
        gridPane.add( txtUbicacion, 0, 8 );
        gridPane.add( txtFecha, 0, 9 );
        gridPane.add( txtPais, 0, 10 );
        gridPane.add( txtEnergia, 0, 11 );
        gridPane.add( txtCovertura, 0, 12 );
        gridPane.add( txtPoblacion, 0, 13 );
//        gridPane.add( txtPlantaProduccionId, 0, 14 );
        
        gridPane.setHgap( 0 );
        gridPane.setVgap( 10 );
        
        gridPane.setPadding( new javafx.geometry.Insets( 10, 10, 10, 10 ) );
        
        dialogo.getDialogPane().setContent( gridPane );
        dialogo.getButtonTypes().setAll( ButtonType.OK, ButtonType.CANCEL );
        
        dialogo.showAndWait().ifPresent( response -> {
            if ( response == ButtonType.OK ) {
                try {
                    // Validar y obtener los valores de los campos
                    int id = ! txtId.getText().isEmpty() ?
                            Integer.parseInt( txtId.getText() ) : 0;
                    BigDecimal capacidad = ! txtCapacidad.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtCapacidad.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal eficiencia = ! txtEficiencia.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtEficiencia.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal radiacion = ! txtRadiacion.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtRadiacion.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal area = ! txtArea.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtArea.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal angulo = ! txtAngulo.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtAngulo.getText() ) ) : BigDecimal.ZERO;
                    String codigo = txtCodigo.getText().isEmpty() ?
                            null : txtCodigo.getText();
                    String pais = txtPais.getText().isEmpty() ?
                            null : txtPais.getText();
                    BigDecimal energia = ! txtEnergia.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtEnergia.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal covertura = ! txtCovertura.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtCovertura.getText() ) ) : BigDecimal.ZERO;
                    BigDecimal poblacion = ! txtPoblacion.getText().isEmpty() ?
                            BigDecimal.valueOf( Double.parseDouble( txtPoblacion.getText() ) ) : BigDecimal.ZERO;
//                    int tipo = ! txtTipo.getText().isEmpty() ?
//                            Integer.parseInt( txtTipo.getText() ) : 5;
                    String ubicacion = txtUbicacion.getText().isEmpty() ?
                            null : txtUbicacion.getText();
                    Date fecha = obtenerFechaSql( txtFecha );
//                    int plantaProduccionId = ! txtPlantaProduccionId.getText().isEmpty() ?
//                            Integer.parseInt( txtPlantaProduccionId.getText() ) : 0;
                    // Llamada al metodo para actualizar los datos
                    energiaSolarDAO.actualizarDatos( id, radiacion, area,
                            angulo, codigo, tipo, ubicacion, capacidad, eficiencia, fecha, pais, energia, covertura,
                            poblacion );
                    
                } catch ( NumberFormatException e ) {
                    mostrarError( "Error en los datos", "Por favor, ingresa valores válidos en los campos numéricos." );
                } catch ( SQLException e ) {
                    mostrarError( "Error de base de datos", "Ocurrió un error al actualizar los datos en la base de datos." );
                    e.printStackTrace();
                }
            }
        } );
    }
    
    // Formato deseado para la fecha
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
    
    // Metodo para extraer la fecha en formato yyyy-MM-dd y convertirla a java.sql.Date
    public Date obtenerFechaSql ( DatePicker fecha ) {
        LocalDate localDate = fecha.getValue();
        
        if ( localDate != null ) {
            // Convertimos la fecha a String con el formato deseado
            String formattedDate = localDate.format( formatter );
            System.out.println( "Fecha formateada: " + formattedDate ); // Para ver el formato
            
            // Convertimos LocalDate a java.sql.Date
            return Date.valueOf( localDate );
        } else {
            System.out.println( "No se ha seleccionado ninguna fecha." );
            return null; // O manejar el caso de acuerdo a tus necesidades
        }
    }
    
    
    // Método para mostrar un mensaje de error
    private void mostrarError ( String titulo, String mensaje ) {
        Alert alerta = new Alert( Alert.AlertType.ERROR );
        alerta.setTitle( titulo );
        alerta.setHeaderText( null );
        alerta.setContentText( mensaje );
        alerta.showAndWait();
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
        icono( "/recargar.png", btnRecargar );
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
    
    // Metodo para mostrar el primer cuadro de diálogo de búsqueda de ID
    private void mostrarDialogoBusqueda () {
        // Crear el cuadro de diálogo para pedir el ID
        TextInputDialog dialogoID = new TextInputDialog();
        dialogoID.setTitle( "Buscar por ID" );
        dialogoID.setHeaderText( "Ingrese el ID que desea buscar" );
        dialogoID.setContentText( "ID:" );
        
        // Mostrar el cuadro y procesar el ID ingresado
        dialogoID.showAndWait().ifPresent( idStr -> {
            try {
                int id = Integer.parseInt( idStr ); // Convertir el ID a entero
                ResultadoBusqueda resultado = energiaSolarDAO.buscarDatos( id ); // Realizar la búsqueda
                
                if ( resultado != null ) {
                    mostrarDialogoResultado( resultado ); // Mostrar los resultados si se encontró el ID
                } else {
                    mostrarMensajeError( "No se encontraron datos para el ID proporcionado." );
                }
            } catch ( NumberFormatException e ) {
                mostrarMensajeError( "Por favor, ingrese un ID válido." );
            } catch ( SQLException e ) {
                mostrarMensajeError( "Error al buscar en la base de datos: " + e.getMessage() );
            }
        } );
    }
    
    // Metodo para mostrar el segundo cuadro de diálogo con los resultados
    private void mostrarDialogoResultado ( ResultadoBusqueda resultado ) {
        Dialog <ButtonType> dialogoResultado = new Dialog <>();
        dialogoResultado.setTitle( "Resultado de Búsqueda" );
        dialogoResultado.setHeaderText( "Datos encontrados para el ID especificado:" );
        
        // Crear el contenido en un GridPane
        GridPane grid = new GridPane();
        grid.setHgap( 10 );
        grid.setVgap( 10 );
        
        // Agregar etiquetas con los datos del resultado
        grid.add( new Label( "Código:" ), 0, 0 );
        grid.add( new Label( resultado.getCodigo() ), 1, 0 );
        
        grid.add( new Label( "Ubicación:" ), 0, 1 );
        grid.add( new Label( resultado.getUbicacion() ), 1, 1 );
        
        grid.add( new Label( "Capacidad instalada:" ), 0, 2 );
        grid.add( new Label( String.valueOf( resultado.getCapacidadinstalada() ) ), 1, 2 );
        
        grid.add( new Label( "Eficiencia:" ), 0, 3 );
        grid.add( new Label( String.valueOf( resultado.getEficiencia() ) ), 1, 3 );
        
        grid.add( new Label( "Tipo de energía:" ), 0, 4 );
        grid.add( new Label( resultado.getTipoenergia() ), 1, 4 );
        
        grid.add( new Label( "Radiación solar promedio:" ), 0, 5 );
        grid.add( new Label( String.valueOf( resultado.getRadiacionsolar() ) ), 1, 5 );
        
        grid.add( new Label( "Área de paneles:" ), 0, 6 );
        grid.add( new Label( String.valueOf( resultado.getAreapaneles() ) ), 1, 6 );
        
        grid.add( new Label( "Ángulo de inclinación:" ), 0, 7 );
        grid.add( new Label( String.valueOf( resultado.getAnguloinclinacion() ) ), 1, 7 );
        
        grid.add( new Label( "Fecha de creación:" ), 0, 8 );
        grid.add( new Label( String.valueOf( resultado.getFechacreacion() ) ), 1, 8 );
        
        dialogoResultado.getDialogPane().setContent( grid );
        dialogoResultado.getDialogPane().getButtonTypes().add( ButtonType.OK );
        dialogoResultado.showAndWait();
    }
    
    // Método para mostrar un mensaje de error
    private void mostrarMensajeError ( String mensaje ) {
        Alert alerta = new Alert( Alert.AlertType.ERROR );
        alerta.setTitle( "Error" );
        alerta.setHeaderText( null );
        alerta.setContentText( mensaje );
        alerta.showAndWait();
    }
    
}
