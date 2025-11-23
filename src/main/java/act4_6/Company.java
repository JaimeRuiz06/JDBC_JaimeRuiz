package act4_6;

public class Company {
    private String cif;
    private String nombre;
    private String sector;

    public Company(String cif, String nombre, String sector) {
        this.cif = cif;
        this.nombre = nombre;
        this.sector = sector;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    @Override
    public String toString() {
        return "Company{" +
                "CIF='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                '}';
    }
}

