package com.alfiankarim.myapplication;

import java.io.Serializable;

public class Dosen implements Serializable {
    String nama_dosen,kodemk,nip;
    public Dosen() {
    }
    public void setNip(String nip) {this.nip = nip;}
    public String getNama_dosen() {
        return nama_dosen;
    }
    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
    public String getKodemk() {
        return kodemk;
    }
    public void setKodemk(String kodemk) {
        this.kodemk = kodemk;
    }
    public String getNip() {return nip;}

}
