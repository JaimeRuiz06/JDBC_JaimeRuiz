package org.basedatos.model;

public class Cliente {
    private String dni;
    private String nombre;
    private String cp;

    public Cliente(String dni, String nombre, String cp) {
        this.dni = dni;
        this.nombre = nombre;
        this.cp = cp;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getCp() { return cp; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCp(String cp) { this.cp = cp; }

    @Override
    public String toString() {
        return dni + " - " + nombre + " - " + cp;
    }
}
