package com.alfiankarim.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Halaman_Scan extends AppCompatActivity implements View.OnClickListener {
private Button btn_scan;
private TextView txt_tampil1,txt_tampil2,txt_tampil3;
private EditText nim_mahasiswa,tanggal;
private Spinner hadir;
private Button btnkirim;
SQLiteDataHelper dbhelper;
    private IntentIntegrator intenIntegerator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__scan);
        btn_scan = findViewById(R.id.buttonscan);
        txt_tampil1 = findViewById(R.id.textView2);
        txt_tampil2 = findViewById(R.id.textView3);
        txt_tampil3 = findViewById(R.id.textView4);
        nim_mahasiswa=findViewById(R.id.nim_mahasiswa);
        tanggal=findViewById(R.id.date);
        btnkirim=findViewById(R.id.btn_kirim);
        hadir=findViewById(R.id.spin_hadir);
        txt_tampil1.setText(getIntent().getStringExtra("Data1"));
        txt_tampil2.setText("Nama Dosen :"+getIntent().getStringExtra("Data2"));
        btnkirim.setOnClickListener(this);
        btn_scan.setOnClickListener(this);
        dbhelper = new SQLiteDataHelper(this);
        dbhelper.getWritableDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if (result.getContents()==null){
                Toast.makeText(this,"Hasil tidak ditemukan", Toast.LENGTH_LONG).show();
            }
            else{
                try{
                    txt_tampil3.setText(result.getContents());
                    nim_mahasiswa.setText(result.getContents());
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this,"Scan Gagal",Toast.LENGTH_LONG).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onClick (View v){
        switch(v.getId()){
            case R.id.buttonscan :
                intenIntegerator = new IntentIntegrator(this);
                intenIntegerator.initiateScan();
                break;
            case R.id.btn_kirim :
                String nim_mhs=nim_mahasiswa.getText().toString();
                String nip_dosen=txt_tampil1.getText().toString();
                String tgl=tanggal.getText().toString();
                String status=hadir.getSelectedItem().toString();
                if(nim_mhs.isEmpty() && nip_dosen.isEmpty() && tgl.isEmpty() && status.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Data Harus diisi semua", Toast.LENGTH_LONG).show();
                }else{
                    PBM pbm= new PBM();
                    pbm.setNim(nim_mhs);
                    pbm.setNip(nip_dosen);
                    pbm.setStatus(status);
                    pbm.setTanggal(tgl);
                    dbhelper.Insert3(pbm);
                    Toast.makeText(getApplicationContext(),"Data Berhasil Dikirim", Toast.LENGTH_LONG).show();
                    txt_tampil3.setText("");
                    tanggal.setText("");
                    nim_mahasiswa.setText("");
                }
                break;
        }

    }
}