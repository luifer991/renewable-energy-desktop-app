package com.renewal.energies.modelo;

import com.renewal.energies.clases.ResultadoBusqueda;
import com.renewal.energies.util.ConeccionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.sql.*;

public class EnergiaSolarDAO {
    
    private Connection connection;
    
    public EnergiaSolarDAO () {
        connection = ConeccionBD.connect();
        if ( connection == null ) {
            System.exit( 1 );
        }
    }
    
    public void guardarDatos ( BigDecimal rad, BigDecimal area, BigDecimal inc,
                               String cod, int tipoEnergia, String ubi,
                               BigDecimal cap, BigDecimal eficiencia, Date fecha ) throws SQLException {
        String query = "CALL insertar_energia_solar (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement( query );
        ps.setBigDecimal( 1, rad );
        ps.setBigDecimal( 2, area );
        ps.setBigDecimal( 3, inc );
        ps.setString( 4, cod );
        ps.setInt( 5, tipoEnergia );
        ps.setString( 6, ubi );
        ps.setBigDecimal( 7, cap );
        ps.setBigDecimal( 8, eficiencia );
        ps.setDate( 9, fecha );
        
        ps.executeUpdate();
        ps.close();
    }
    
    public void guardarPais ( String pais, BigDecimal energia, BigDecimal covertura,
                              BigDecimal poblacion, int plantaproduccionid ) throws SQLException {
        String query = "INSERT INTO pais " +
                "(nombre, energiarequerida, nivelcovertura,poblacion,plantaproduccionid) " +
                "VALUES (?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement( query );
        ps.setString( 1, pais );
        ps.setBigDecimal( 2, energia );
        ps.setBigDecimal( 3, covertura );
        ps.setBigDecimal( 4, poblacion );
        ps.setInt( 5, plantaproduccionid );
        
        ps.executeUpdate();
        ps.close();
    }
    
    public void actualizarDatos ( int id, BigDecimal radiacion, BigDecimal area,
                                  BigDecimal angulo, String codigo, int tipo, String ubicacion, BigDecimal capacidad,
                                  BigDecimal eficiencia, Date fecha ) throws SQLException {
        
        String query = "CALL actualizar_energia_solar (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement( query );
        ps.setInt( 1, id );
        ps.setBigDecimal( 2, radiacion );
        ps.setBigDecimal( 3, area );
        ps.setBigDecimal( 4, angulo );
        ps.setString( 5, codigo );
        ps.setInt( 6, tipo );
        ps.setString( 7, ubicacion );
        ps.setBigDecimal( 8, capacidad );
        ps.setBigDecimal( 9, eficiencia );
        ps.setDate( 10, fecha );
        
        ps.executeUpdate();
        ps.close();
    }
    
    public void actualizarPais ( int Id, String pais, BigDecimal energia, BigDecimal covertura,
                                 BigDecimal poblacion, int plantaproduccionid ) throws SQLException {
        String query = "UPDATE pais" +
                "SET nombre = ?, energiarequerida = ?, nivelcovertura = ?, poblacion = ?, plantaproduccionid = ?" +
                "WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement( query );
        ps.setString( 1, pais );
        ps.setBigDecimal( 2, energia );
        ps.setBigDecimal( 3, covertura );
        ps.setBigDecimal( 4, poblacion );
        ps.setInt( 5, plantaproduccionid );
        
        ps.executeUpdate();
        ps.close();
    }
    
    public ObservableList <ResultadoBusqueda> obtenerDatos () throws SQLException {
        ObservableList <ResultadoBusqueda> data = FXCollections.observableArrayList();
        String query = "SELECT p.id, e.nombre as codigo, p.ubicacion, p.capacidadinstalada, p.eficiencia, " +
                "t.nombre as tipoenergia, " +
                "s.radiacionsolarpromedio as radiacionsolar, " +
                "s.areapaneles, s.anguloinclinacion, " +
                "p.fechacreacion " +
                "FROM plantaproduccion p " +
                "JOIN energiasrenovables e " +
                "ON e.id = p.id " +
                "JOIN tipoenergia t " +
                "ON t.id = e.tipoenergiaid " +
                "JOIN energiasolar s " +
                "ON s.id = e.id";
        PreparedStatement pstmt = connection.prepareStatement( query );
        ResultSet rs = pstmt.executeQuery();
        while ( rs.next() ) {
            int id = rs.getInt( "id" );
            String codigo = rs.getString( "codigo" );
            String ubicacion = rs.getString( "ubicacion" );
            BigDecimal capacidad = rs.getBigDecimal( "capacidadinstalada" );
            BigDecimal eficiencia = rs.getBigDecimal( "eficiencia" );
            String tipoEnergia = rs.getString( "tipoenergia" );
            BigDecimal radiacion = rs.getBigDecimal( "radiacionsolar" );
            BigDecimal area = rs.getBigDecimal( "areapaneles" );
            BigDecimal angulo = rs.getBigDecimal( "anguloinclinacion" );
            Date fecha = rs.getDate( "fechacreacion" );
            data.add( new ResultadoBusqueda( id, codigo, ubicacion, capacidad, eficiencia,
                    tipoEnergia, radiacion, area, angulo, fecha ) );
        }
        rs.close();
        pstmt.close();
        return data;
    }
    
    public ResultadoBusqueda buscarDatos ( int i ) throws SQLException {
        String query = "SELECT p.id, e.nombre AS codigo, p.ubicacion, p.capacidadinstalada, p.eficiencia, " +
                "t.nombre AS tipoenergia, s.radiacionsolarpromedio AS radiacionsolar, " +
                "s.areapaneles, s.anguloinclinacion, p.fechacreacion " +
                "FROM plantaproduccion p " +
                "JOIN energiasrenovables e ON e.id = p.id " +
                "JOIN tipoenergia t ON t.id = e.tipoenergiaid " +
                "JOIN energiasolar s ON s.id = e.id " +
                "WHERE p.id = ?";
        ResultadoBusqueda resultado = null;
        try ( PreparedStatement pstmt = connection.prepareStatement( query ) ) {
            pstmt.setInt( 1, i );
            try ( ResultSet rs = pstmt.executeQuery() ) {
                if ( rs.next() ) {
                    resultado = new ResultadoBusqueda(
                            rs.getInt( "id" ),
                            rs.getString( "codigo" ),
                            rs.getString( "ubicacion" ),
                            rs.getBigDecimal( "capacidadinstalada" ),
                            rs.getBigDecimal( "eficiencia" ),
                            rs.getString( "tipoenergia" ),
                            rs.getBigDecimal( "radiacionsolar" ),
                            rs.getBigDecimal( "areapaneles" ),
                            rs.getBigDecimal( "anguloinclinacion" ),
                            rs.getDate( "fechacreacion" )
                    );
                }
            }
        }
        return resultado;
    }
    
    
    // Método para obtener la información de la base de datos
    public Double obtenerInformacionDeBaseDeDatos () {
        double resultado = 0;
        String query = "SELECT areapaneles FROM energiasolar"; // Ajusta la consulta según tu necesidad
        
        try ( Connection conn = ConeccionBD.connect();
              PreparedStatement pstmt = conn.prepareStatement( query );
              ResultSet rs = pstmt.executeQuery() ) {
            
            // Si hay un resultado, obtenemos el nombre del cliente
            if ( rs.next() ) {
                resultado = rs.getDouble( 1 );
            }
        } catch ( SQLException e ) {
            System.out.println( "Error en la consulta: " + e.getMessage() );
        }
        
        return resultado;
    }
    
    public void eliminarDatos ( int i ) {
        String query = "Call borrar_energia_solar(?)";
        try {
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setInt( 1, i );
            ps.executeUpdate();
            ps.close();
        } catch ( SQLException e ) {
            throw new RuntimeException( e );
        }
    }
}
