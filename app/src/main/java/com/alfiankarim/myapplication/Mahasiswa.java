package com.alfiankarim.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class Mahasiswa implements Serializable {

     String nim,nama,kelas,kode_mk;

    public  Mahasiswa(){

    }
    @NonNull
    public String getNim() {
        return nim;
    }

    public void setNim(@NonNull String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getKode_mk() {
        return kode_mk;
    }

    public void setKode_mk(String kode_mk) {
        this.kode_mk = kode_mk;
    }

    public Mahasiswa(String nama, String nim, String kelas, String kode_mk){
        this.nama= nama;
        this.nim= nim;
        this.kelas=kelas;
        this.kode_mk=kode_mk;
    }
}
