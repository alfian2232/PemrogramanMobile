package com.alfiankarim.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class SQLiteDataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="absensi.db";
    private static final int DATABASE_VERSION=1;
    SQLiteDatabase db;
    public SQLiteDataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql="CREATE table mahasiswa(nim varchar primary key,nama text not null,kelas varchar not null,kode_mk text not null);";
    String sql2="CREATE TABLE dosen(nip varchar primary key,nama_dosen text,kode_mk text);";
    String sql3="CREATE TABLE user(email varchar primary key,pass varchar not null);";
    String sql4="CREATE TABLE pbm(nim varchar not null,nip varchar not null, status text not null,tanggal date not null, foreign key(nim) REFERENCES mahasiswa(nim)," +
            "foreign key(nip) REFERENCES dosen(nip));";
    Log.d("Data","onCreate :" +sql);
    Log.d("Data","onCreate :" +sql2);
    db.execSQL(sql);
    db.execSQL(sql2);
    db.execSQL(sql3);
    db.execSQL(sql4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS pbm");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS dosen");
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public void Insert(Mahasiswa mhs){
        String kelas=mhs.getKelas();
        String nama=mhs.getNama();
        String nim=mhs.getNim();
        String kode_mk=mhs.getKode_mk();
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("INSERT INTO mahasiswa(nama,nim,kelas,kode_mk) values('"+nama+"','"+nim+"','"+kelas+"','"+kode_mk+"')");
        db.close();
    }
    public void Insert2(Dosen dsn){
        String NIP= dsn.getNip();
        String nama_dosen=dsn.getNama_dosen();
        String kode_mk= dsn.getKodemk();
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("INSERT INTO dosen(nip,nama_dosen,kode_mk) values('"+NIP+"','"+nama_dosen+"','"+kode_mk+"')");
        db.close();
    }

    public Boolean insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("pass", password);
        long insert = db.insert("user", null, contentValues);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    public void Insert3(PBM pbm){
        String nip1= pbm.getNip();
        String nim1=pbm.getNim();
        String status= pbm.getStatus();
        String tgl=pbm.getTanggal();
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("INSERT INTO pbm(nim,nip,status,tanggal) values('"+nip1+"','"+nim1+"','"+status+"','"+tgl+"')");
        db.close();
    }
}
