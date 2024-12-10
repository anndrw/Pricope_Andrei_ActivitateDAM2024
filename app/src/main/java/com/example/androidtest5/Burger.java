package com.example.androidtest5;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Burger implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String denumire;
    private int pret;
    private float gramaj;
    private Date dataExpirare;

    public Burger(String denumire, int pret, float gramaj, Date dataExpirare) {
        this.denumire = denumire;
        this.pret = pret;
        this.gramaj = gramaj;
        this.dataExpirare = dataExpirare;
    }

    protected Burger(Parcel in) {
        denumire = in.readString();
        pret = in.readInt();
        gramaj = in.readFloat();
        this.dataExpirare = new Date();
    }

    public static final Creator<Burger> CREATOR = new Creator<Burger>() {
        @Override
        public Burger createFromParcel(Parcel in) {
            return new Burger(in);
        }

        @Override
        public Burger[] newArray(int size) {
            return new Burger[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public float getGramaj() {
        return gramaj;
    }

    public void setGramaj(float gramaj) {
        this.gramaj = gramaj;
    }

    public Date getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(Date dataExpirare) {
        this.dataExpirare = dataExpirare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Burger{");
        sb.append("denumire='").append(denumire).append('\'');
        sb.append(", pret=").append(pret);
        sb.append(", gramaj=").append(gramaj);
        sb.append(", dataExpirare=").append(dataExpirare);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeInt(pret);
        dest.writeFloat(gramaj);
        dest.writeLong(this.dataExpirare != null ? this.dataExpirare.getTime() : -1);
    }
}
