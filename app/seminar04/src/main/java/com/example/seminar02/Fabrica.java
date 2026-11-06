package com.example.seminar02;

import android.os.Parcel;
import android.os.Parcelable;

public class Fabrica implements Parcelable {

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
        this.lucreazaInTure=lucreazaInTure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Fabrica(Parcel in)
    {
        nrAngajati=in.readInt();
        numeFabrica=in.readString();
        codFabrica=in.readInt();
        domeniuActivitate=in.readString();
        lucreazaInTure=in.readByte()!=0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(nrAngajati);
        dest.writeString(numeFabrica);
        dest.writeInt(codFabrica);
        dest.writeString(domeniuActivitate);
        dest.writeByte((byte)(lucreazaInTure ? 1:0));
    }

    public static final Creator<Fabrica> CREATOR=new Creator<Fabrica>(){
        @Override
        public Fabrica createFromParcel(Parcel in){
            return new Fabrica(in);
        }
        @Override
        public Fabrica[] newArray(int size){
            return new Fabrica[size];
        }
    };
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
