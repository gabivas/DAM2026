package ro.ase.lab;

import java.io.Serializable;

public class Masina implements Serializable {
    private String serie;
    private String marca;
    private int cp;

    public Masina(String serie, int cp, String marca) {
        this.serie = serie;
        this.cp = cp;
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "serie='" + serie + '\'' +
                ", marca='" + marca + '\'' +
                ", cp=" + cp +
                '}';
    }
}
