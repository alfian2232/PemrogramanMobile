package com.alfiankarim.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class Detail_PBM extends AppCompatActivity {
private TextView tv_nama_mhs,tv_nama_dosen,tv_nim,tv_nip,tv_kelas,tv_mk,tv_status,tv_tgl;
ArrayAdapter<String> Daftarid;
ArrayList<String> LstID= new ArrayList<String>();
SQLiteDataHelper dbhelper;
Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__p_b_m);
        tv_nama_mhs=findViewById(R.id.tampil_nama);
        tv_nama_dosen=findViewById(R.id.tampil_namadosen);
        tv_nim=findViewById(R.id.tampil_nim);
        tv_nip=findViewById(R.id.tampil_nip);
        tv_kelas=findViewById(R.id.tampil_kelas);
        tv_mk=findViewById(R.id.tampil_mk);
        tv_status=findViewById(R.id.tampil_status);
        tv_tgl=findViewById(R.id.tampil_tgl);

        dbhelper= new SQLiteDataHelper(this);
        SQLiteDatabase db= dbhelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT p.nim,p.nip,p.tanggal,p.status,m.kelas,m.nama,d.kode_mk,d.nama_dosen FROM pbm p LEFT JOIN mahasiswa m ON p.nim=m.nim " +
                "LEFT JOIN dosen d ON p.nip=d.nip WHERE m.nim='"+getIntent().getStringExtra("nim")+"'", null);
        if(cursor != null && cursor.moveToFirst()) {
            cursor.moveToPosition(0);
            tv_nim.setText(cursor.getString(0).toString());
            tv_nip.setText(cursor.getString(1).toString());
            tv_tgl.setText(cursor.getString(2).toString());
            tv_status.setText(cursor.getString(3).toString());
            tv_kelas.setText(cursor.getString(4).toString());
            tv_nama_mhs.setText(cursor.getString(5).toString());
            tv_mk.setText(cursor.getString(6).toString());
            tv_nama_dosen.setText(cursor.getString(7).toString());
        }
    }
    }
