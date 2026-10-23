package com.example.seminar02;

import java.io.Serializable;

public class Fabrica implements Serializable {

    int nrAngajati;
    String numeFabrica;
    int codFabrica;
    String domeniuActivitate;

    boolean lucreazaInTure;

    public Fabrica(int nrAngajati, String numeFabrica, int codFabrica, String domeniuActivitate, boolean lucreazaInTure) {
        this.nrAngajati = nrAngajati;
        this.numeFabrica = numeFabrica;
        this.codFabrica = codFabrica;
        this.domeniuActivitate = domeniuActivitate;
        this.lucreazaInTure=true;
    }
    public String getNumeFabrica() {
        return numeFabrica;
    }

    public int getNrAngajati() {
        return nrAngajati;
    }

    public int getCodFabrica() {
        return codFabrica;
    }

    public String getDomeniuActivitate() {
        return domeniuActivitate;
    }

    public boolean isLucreazaInTure() {
        return lucreazaInTure;
    }

    @Override
    public String toString() {
        return "Fabrica{" +
                "nrAngajati=" + nrAngajati +
                ", numeFabrica='" + numeFabrica + '\'' +
                ", codFabrica=" + codFabrica +
                ", domeniuActivitate='" + domeniuActivitate + '\'' +
                ", lucreazaInTure=" + lucreazaInTure +
                '}';
    }
}
