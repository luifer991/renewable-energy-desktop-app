package com.renewal.energies.clases;

import java.math.BigDecimal;

public class EnergiaSolar extends EnergiasRenovables {
    
    private BigDecimal radiacionSolarPromedio;
    private BigDecimal areaPaneles;
    private BigDecimal anguloInclinacion;
    
    public EnergiaSolar ( BigDecimal radiacionSolarPromedio, BigDecimal areaPaneles, BigDecimal anguloInclinacion,
                          int id, String nombre, int tipoEnergiaId ) {
        super( id, nombre, tipoEnergiaId );
        this.radiacionSolarPromedio = radiacionSolarPromedio;
        this.areaPaneles = areaPaneles;
        this.anguloInclinacion = anguloInclinacion;
    }
    
    public void EnergiaSolar () {
    }
    
    
    public BigDecimal getRadiacionSolarPromedio () {
        return radiacionSolarPromedio;
    }
    
    public void setRadiacionSolarPromedio ( double radiacionSolarPromedio ) {
        this.radiacionSolarPromedio = BigDecimal.valueOf( radiacionSolarPromedio );
    }
    
    public BigDecimal getAreaPaneles () {
        return areaPaneles;
    }
    
    public void setAreaPaneles ( double areaPaneles ) {
        this.areaPaneles = BigDecimal.valueOf( areaPaneles );
    }
    
    public BigDecimal getAnguloInclinacion () {
        return anguloInclinacion;
    }
    
    public void setAnguloInclinacion ( double anguloInclinacion ) {
        this.anguloInclinacion = BigDecimal.valueOf( anguloInclinacion );
    }
}
