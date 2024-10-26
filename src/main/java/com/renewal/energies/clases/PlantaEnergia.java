package com.renewal.energies.clases;

import java.math.BigDecimal;
import java.util.Date;

public class PlantaEnergia {
    
    private String ubicacion;
    private BigDecimal capacidadInstalada;
    private BigDecimal eficiencia;
    private Date fechaCreacion;
    private EnergiasRenovables energiasRenovables;
    
    public PlantaEnergia () {
    }
    
    public PlantaEnergia ( String ubicacion, BigDecimal capacidadInstalada,
                           BigDecimal eficiencia, Date fechaCreacion, EnergiasRenovables energiasRenovables ) {
        this.ubicacion = ubicacion;
        this.capacidadInstalada = capacidadInstalada;
        this.eficiencia = eficiencia;
        this.fechaCreacion = fechaCreacion;
        this.energiasRenovables = energiasRenovables;
    }
    
    public String getUbicacion () {
        return ubicacion;
    }
    
    public void setUbicacion ( String ubicacion ) {
        this.ubicacion = ubicacion;
    }
    
    public BigDecimal getCapacidadInstalada () {
        return capacidadInstalada;
    }
    
    public void setCapacidadInstalada ( double capacidadInstalada ) {
        this.capacidadInstalada = BigDecimal.valueOf( capacidadInstalada );
    }
    
    public BigDecimal getEficiencia () {
        return eficiencia;
    }
    
    public void setEficiencia ( double eficiencia ) {
        this.eficiencia = BigDecimal.valueOf( eficiencia );
    }
    
    public Date getFechaCreacion () {
        return fechaCreacion;
    }
    
    public void setFechaCreacion ( Date fechaCreacion ) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public EnergiasRenovables getEnergiasRenovables () {
        return energiasRenovables;
    }
    
    public void setEnergiasRenovables ( EnergiasRenovables energiasRenovables ) {
        this.energiasRenovables = energiasRenovables;
    }
}
