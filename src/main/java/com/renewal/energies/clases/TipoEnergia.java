package com.renewal.energies.clases;

public class TipoEnergia {
    private int Id;
    private String nombre;
    
    public TipoEnergia () {
    }
    
    public TipoEnergia ( int id, String nombre ) {
        Id = id;
        this.nombre = nombre;
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
}
