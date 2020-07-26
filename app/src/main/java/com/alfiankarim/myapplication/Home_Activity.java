package com.alfiankarim.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;



public class Home_Activity extends AppCompatActivity  {
    private EditText txt_nama;
    private EditText txt_nim;
    private Spinner cb_kelas;
    private Spinner cb_kodemk;
    private Button btn_simpan;
    private ImageView img_barcode;
    SQLiteDataHelper dbHelper;
    String edit;
    String konten;
    MultiFormatWriter multiFormatWriter= new MultiFormatWriter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        String name = getIntent().getStringExtra("name");

        cb_kodemk = findViewById(R.id.kode_mk);
        cb_kelas = findViewById(R.id.kelas);
        txt_nim = findViewById(R.id.nim);
        txt_nama = findViewById(R.id.nama);
        img_barcode = findViewById(R.id.barcode);
        btn_simpan = findViewById(R.id.btnsimpan);
         dbHelper = new SQLiteDataHelper(this);

        btn_simpan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();
                edit=txt_nim.getText().toString();
                if(edit.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kolom tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }else{
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setNama(txt_nama.getText().toString());
                    mhs.setNim(txt_nim.getText().toString());
                    mhs.setKelas(cb_kelas.getSelectedItem().toString());
                    mhs.setKode_mk(cb_kodemk.getSelectedItem().toString());
                    dbHelper.Insert(mhs);
                    Toast.makeText(getApplicationContext(),"Data Tersimpan",Toast.LENGTH_LONG).show();

                    try{
                        BitMatrix bitMatrix= multiFormatWriter.encode(edit, BarcodeFormat.QR_CODE,700,700);
                        BarcodeEncoder encoder=new BarcodeEncoder();
                        Bitmap bitmap= encoder.createBitmap(bitMatrix);
                        img_barcode.setImageBitmap(bitmap);
                    }catch (WriterException e){
                        e.printStackTrace();
                    }
                    txt_nama.setText("");
                    txt_nim.setText("");
                }
            }
        });

    }
}
