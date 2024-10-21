package com.renewal.energies.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConeccionBD {
    private static final String URL = "jdbc:postgresql://localhost:5432/renewal-energies";
    private static final String USER = "root";
    private static final String PASSWORD = "158365402Luifer@";
    
    public static Connection connect () {
        Connection conn = null;
        try {
            // Cargar el driver de PostgreSQL
            Class.forName( "org.postgresql.Driver" );
            // Establecer la conexión
            conn = DriverManager.getConnection( URL, USER, PASSWORD );
            System.out.println( "Conexión exitosa a la base de datos" );
        } catch ( SQLException e ) {
            System.out.println( "Error al conectar a la base de datos: " + e.getMessage() );
        } catch ( ClassNotFoundException e ) {
            System.out.println( "No se encontró el driver JDBC: " + e.getMessage() );
        }
        
        return conn;
    }
}
