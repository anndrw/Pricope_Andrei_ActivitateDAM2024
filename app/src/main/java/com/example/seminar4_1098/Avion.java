package com.example.seminar4_1098;

public class Avion {
    private String denumire;
    private int nrLocuri;
    private float consum;

    private boolean inAir;
    private String anFabricare;

    private boolean tipConfort;

    private boolean termeni;

    public Avion(String denumire, boolean termeni, boolean tipConfort, String anFabricare, boolean inAir, float consum, int nrLocuri) {
        this.denumire = denumire;
        this.termeni = termeni;
        this.tipConfort = tipConfort;
        this.anFabricare = anFabricare;
        this.inAir = inAir;
        this.consum = consum;
        this.nrLocuri = nrLocuri;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }

    public float getConsum() {
        return consum;
    }

    public void setConsum(float consum) {
        this.consum = consum;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public String getAnFabricare() {
        return anFabricare;
    }

    public void setAnFabricare(String anFabricare) {
        this.anFabricare = anFabricare;
    }

    public boolean isTipConfort() {
        return tipConfort;
    }

    public void setTipConfort(boolean tipConfort) {
        this.tipConfort = tipConfort;
    }

    public boolean isTermeni() {
        return termeni;
    }

    public void setTermeni(boolean termeni) {
        this.termeni = termeni;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Avion{");
        sb.append("denumire='").append(denumire).append('\'');
        sb.append(", nrLocuri=").append(nrLocuri);
        sb.append(", consum=").append(consum);
        sb.append(", inAir=").append(inAir);
        sb.append(", anFabricare='").append(anFabricare).append('\'');
        sb.append(", tipConfort=").append(tipConfort);
        sb.append(", termeni=").append(termeni);
        sb.append('}');
        return sb.toString();
    }
}
