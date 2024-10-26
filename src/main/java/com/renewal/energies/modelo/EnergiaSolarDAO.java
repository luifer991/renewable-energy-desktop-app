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
    
    public void guardar ( BigDecimal rad, BigDecimal area, BigDecimal inc,
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
        PreparedStatement pstmt = connection.prepareStatement(query);
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
}
