package com.renewal.energies.clases;

public abstract class EnergiasRenovables {
    
    private int Id;
    private String nombre;
    public int tipoEnergiaId;
    
    public EnergiasRenovables () {
    }
    
    public EnergiasRenovables ( int id, String nombre, int tipoEnergiaId ) {
        Id = id;
        this.nombre = nombre;
        this.tipoEnergiaId = tipoEnergiaId;
    }
    
    public int getId () {
        return Id;
    }
    
    public void setId ( int id ) {
        Id = id;
    }
    
    public String getNombre () {
        return nombre;
    }
    
    public void setNombre ( String nombre ) {
        this.nombre = nombre;
    }
    
    public int getTipoEnergiaId () {
        return tipoEnergiaId;
    }
    
    public void setTipoEnergiaId ( int tipoEnergiaId ) {
        this.tipoEnergiaId = tipoEnergiaId;
    }
}
