package com.alfiankarim.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Registration_Dosen_Activity extends AppCompatActivity {
    private EditText edt_nip,edt_nama,edt_psw,edt_email;
    private Spinner kodemk;
    private Button btn_reg1;
    private TextView sign_in;
    private SQLiteDataHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__dosen_);
        edt_nip=findViewById(R.id.nip_reg);
         edt_nama= findViewById(R.id.nama_dosen);
       kodemk= findViewById(R.id.spin_mk);
         edt_psw=findViewById(R.id.password_reg1);
        btn_reg1=findViewById(R.id.btn_reg1);
         sign_in= findViewById(R.id.signin_txt1);
         edt_email=findViewById(R.id.email_reg1);

         dbhelper= new SQLiteDataHelper(this);

        btn_reg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbhelper.getWritableDatabase();
                String nip= edt_nip.getText().toString();
                String nam_dosed=edt_nama.getText().toString();
                String kore_mk=kodemk.getSelectedItem().toString();
                String pass=edt_psw.getText().toString().trim();
                String mEmail=edt_email.getText().toString().trim();
                if(nip.isEmpty() && nam_dosed.isEmpty() && kore_mk.isEmpty() && pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Kolom harus diisi semua",Toast.LENGTH_LONG).show();
                }else{
                        Boolean daftar = dbhelper.insertUser(mEmail, pass);
                        if (daftar == true) {
                            Dosen dsn = new Dosen();
                            dsn.setNip(nip);
                            dsn.setNama_dosen(nam_dosed);
                            dsn.setKodemk(kore_mk);
                            dbhelper.Insert2(dsn);
                            Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                            Intent loginIntent = new Intent(Registration_Dosen_Activity.this, Halaman_Scan.class);
                            loginIntent.putExtra("Data1",nip);
                            loginIntent.putExtra("Data2",nam_dosed);
                            startActivity(loginIntent);
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Daftar Gagal", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}