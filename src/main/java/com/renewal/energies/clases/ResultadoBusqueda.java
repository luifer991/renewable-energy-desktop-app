package com.renewal.energies.clases;

import java.math.BigDecimal;
import java.sql.Date;

public class ResultadoBusqueda {
    private int id;
    private String codigo;
    private String ubicacion;
    private BigDecimal capacidadinstalada;
    private BigDecimal eficiencia;
    private String tipoenergia;
    private BigDecimal radiacionsolar;
    private BigDecimal areapaneles;
    private BigDecimal anguloinclinacion;
    private Date fechacreacion;
    
    public ResultadoBusqueda ( int id, String codigo, String ubicacion,
                               BigDecimal capacidadInstalada, BigDecimal eficiencia,
                               String tipoEnergia, BigDecimal radiacionSolarAVG,
                               BigDecimal areaPaneles, BigDecimal anguloInclinacion, Date fechaCreacion ) {
        this.id = id;
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.capacidadinstalada = capacidadInstalada;
        this.eficiencia = eficiencia;
        this.tipoenergia = tipoEnergia;
        this.radiacionsolar = radiacionSolarAVG;
        this.areapaneles = areaPaneles;
        this.anguloinclinacion = anguloInclinacion;
        this.fechacreacion = fechaCreacion;
    }
    
    public int getId () {
        return id;
    }
    
    public void setId ( int id ) {
        this.id = id;
    }
    
    public String getCodigo () {
        return codigo;
    }
    
    public void setCodigo ( String codigo ) {
        this.codigo = codigo;
    }
    
    public String getUbicacion () {
        return ubicacion;
    }
    
    public void setUbicacion ( String ubicacion ) {
        this.ubicacion = ubicacion;
    }
    
    public BigDecimal getCapacidadinstalada () {
        return capacidadinstalada;
    }
    
    public void setCapacidadinstalada ( BigDecimal capacidadinstalada ) {
        this.capacidadinstalada = capacidadinstalada;
    }
    
    public BigDecimal getEficiencia () {
        return eficiencia;
    }
    
    public void setEficiencia ( BigDecimal eficiencia ) {
        this.eficiencia = eficiencia;
    }
    
    public String getTipoenergia () {
        return tipoenergia;
    }
    
    public void setTipoenergia ( String tipoenergia ) {
        this.tipoenergia = tipoenergia;
    }
    
    public BigDecimal getRadiacionsolar () {
        return radiacionsolar;
    }
    
    public void setRadiacionsolar ( BigDecimal radiacionsolar ) {
        this.radiacionsolar = radiacionsolar;
    }
    
    public BigDecimal getAreapaneles () {
        return areapaneles;
    }
    
    public void setAreapaneles ( BigDecimal areapaneles ) {
        this.areapaneles = areapaneles;
    }
    
    public BigDecimal getAnguloinclinacion () {
        return anguloinclinacion;
    }
    
    public void setAnguloinclinacion ( BigDecimal anguloinclinacion ) {
        this.anguloinclinacion = anguloinclinacion;
    }
    
    public Date getFechacreacion () {
        return fechacreacion;
    }
    
    public void setFechacreacion ( Date fechacreacion ) {
        this.fechacreacion = fechacreacion;
    }
}
