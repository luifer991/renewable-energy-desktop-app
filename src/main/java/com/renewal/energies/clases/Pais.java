package com.renewal.energies.clases;

import java.math.BigDecimal;

public class Pais {
    
    private String nombre;
    private BigDecimal energiaRequerida;
    private BigDecimal nivelCovertura;
    private BigDecimal poblacion;
    private int plantaproduccionid;
    
    public Pais () {
    }
    
    public Pais ( String nombre, double energiaRequerida,
                  double nivelCovertura, double poblacion,
                  int plantaproduccionid, int tipoenergiaid ) {
        this.nombre = nombre;
        this.energiaRequerida = BigDecimal.valueOf( energiaRequerida );
        this.nivelCovertura = BigDecimal.valueOf( nivelCovertura );
        this.poblacion = BigDecimal.valueOf( poblacion );
        this.plantaproduccionid = plantaproduccionid;
    }
    
    public String getNombre () {
        return nombre;
    }
    
    public void setNombre ( String nombre ) {
        this.nombre = nombre;
    }
    
    public BigDecimal getEnergiaRequerida () {
        return energiaRequerida;
    }
    
    public void setEnergiaRequerida ( double energiaRequerida ) {
        this.energiaRequerida = BigDecimal.valueOf( energiaRequerida );
    }
    
    public BigDecimal getNivelCovertura () {
        return nivelCovertura;
    }
    
    public void setNivelCovertura ( double nivelCovertura ) {
        this.nivelCovertura = BigDecimal.valueOf( nivelCovertura );
    }
    
    public BigDecimal getPoblacion () {
        return poblacion;
    }
    
    public void setPoblacion ( double poblacion ) {
        this.poblacion = BigDecimal.valueOf( poblacion );
    }
    
    public int getPlantaproduccionid () {
        return plantaproduccionid;
    }
    
    public void setPlantaproduccionid ( int plantaproduccionid ) {
        this.plantaproduccionid = plantaproduccionid;
    }
}
