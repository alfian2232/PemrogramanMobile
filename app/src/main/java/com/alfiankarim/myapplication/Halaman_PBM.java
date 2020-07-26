package com.alfiankarim.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Halaman_PBM extends AppCompatActivity {
private ListView listView;
SQLiteDataHelper dbhelper;
String[] daftar;
protected Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman__p_b_m);
        listView=findViewById(R.id.listView1);
        dbhelper= new SQLiteDataHelper(this);
        SQLiteDatabase db= dbhelper.getReadableDatabase();
        cursor=db.rawQuery("SELECT p.nim,p.nip,p.tanggal,p.status,m.kelas,m.nama,d.kode_mk,d.nama_dosen FROM pbm p LEFT JOIN mahasiswa m ON p.nim=m.nim " +
                "LEFT JOIN dosen d ON p.nip=d.nip",null);
        daftar=new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView arg0,View arg1,int arg2,long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Halaman_PBM.this);
                builder.setTitle("Pilihan");

                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), Detail_PBM.class);
                                i.putExtra("nim", cursor.getString(1).toString());
                                startActivity(i);
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}