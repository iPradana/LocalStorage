package com.team67.localstorage;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText txtID1,txtID2,txtNama1,txtNama2;
    Button btnInsert1, btnInsert2;
    Button btnUpdet1, btnUpdet2;
    Button btnDelete1, btndelete2;
    Button btnMhs, btnDsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        txtID1 = (EditText) findViewById(R.id.txtID);
        txtID2 = (EditText) findViewById(R.id.txtID2);
        txtNama1 = (EditText) findViewById(R.id.txtNama);
        txtNama2 = (EditText) findViewById(R.id.txtNama2);
    }

    public void btnInsert1(View view){
        boolean isInserted = myDb.insertData1(txtNama1.getText().toString());
        if(isInserted == true) {
            Toast.makeText(MainActivity.this, "Data Mahasiswa Inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Mahasiswa Not Inserted",Toast.LENGTH_LONG).show();
        }
    }

    public void btnInsert2(View view){
        boolean isInserted = myDb.insertData2(txtNama2.getText().toString());
        if(isInserted == true) {
            Toast.makeText(MainActivity.this, "Data Dosen Inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Dosen Not Inserted",Toast.LENGTH_LONG).show();
        }
    }

    public void btnUpdate1(View view){
        boolean isUpdated = myDb.updateData1(txtID1.getText().toString(), txtNama1.getText().toString());
        if(isUpdated == true) {
            Toast.makeText(MainActivity.this, "Data Mahasiswa Updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Mahasiswa Not Updated", Toast.LENGTH_LONG).show();
        }
    }

    public void btnUpdate2(View view){
        boolean isUpdated = myDb.updateData2(txtID2.getText().toString(), txtNama2.getText().toString());
        if(isUpdated == true) {
            Toast.makeText(MainActivity.this, "Data Dosen Updated", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Dosen Not Updated", Toast.LENGTH_LONG).show();
        }
    }

    public void btnDelete1(View view){
        Integer deleteRows = myDb.deleteData1(txtID1.getText().toString());
        if(deleteRows>0) {
            Toast.makeText(MainActivity.this, "Data Mahasiswa Deleted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Mahasiswa Not Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void btnDelete2(View view){
        Integer deleteRows = myDb.deleteData2(txtID2.getText().toString());
        if(deleteRows>0) {
            Toast.makeText(MainActivity.this, "Data Mahasiswa Deleted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data Mahasiswa Not Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void btnViewMhs(View view){
        Cursor res = myDb.getDataMahasiswa();
        if(res.getCount() == 0){
            showMessage("ERROR", "NOTHING FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Nama : "+res.getString(1)+"\n");
        }
        showMessage("Data Mahasiswa",buffer.toString());
    }

    public void btnViewDsn(View view){
        Cursor res = myDb.getDataDosen();
        if(res.getCount() == 0){
            showMessage("ERROR", "NOTHING FOUND");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("ID : "+res.getString(0)+"\n");
            buffer.append("Nama : "+res.getString(1)+"\n");
        }
        showMessage("Data Dosen",buffer.toString());
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
