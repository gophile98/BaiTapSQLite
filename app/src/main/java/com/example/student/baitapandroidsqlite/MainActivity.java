package com.example.student.baitapandroidsqlite;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter adapter;
    ArrayList arrayList = new ArrayList();
    ArrayList arrayListLop = new ArrayList();
    ArrayAdapter arrayAdapterspiner;
    StuManager db;

    Dialog dialog;
    Spinner spinner;
    EditText txtten;
    EditText txtsubject;
    Button btnthemsv;
    TextView tvid;
    EditText txtten2;
    EditText txtsubject2;
    Button btnsuasv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById( R.id.spnlop );
        txtten = findViewById(R.id.txtname);
        txtsubject = findViewById(R.id.txtsubject );
        btnthemsv = findViewById(R.id.themsv);
        tvid = findViewById(R.id.tvid);
        txtten2 = findViewById(R.id.txtname2);
        txtsubject2 = findViewById(R.id.txtsubject2 );
        btnsuasv = findViewById(R.id.btnsuasv);

        db = new StuManager(this, "SVDB.sqlite", null, 1);
        db.action("create table if not exists Lop(id integer primary key autoincrement, name text)");

        db.action("create table if not exists SinhVien(id integer primary key autoincrement, name text, class_name text, subject text)");

        //        db.action("insert into SinhVien values(null, 'Trinh Duc Dat', 'DHKTPM12A', 'mssv: 16043051')");

        listView =(ListView) findViewById(R.id.lvlop);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        arrayAdapterspiner = new ArrayAdapter( this, android.R.layout.simple_spinner_item, arrayListLop );
        loadDB();

        btnthemsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtten.getText().toString() != null && txtsubject.getText().toString() != null){
                    SinhVien sinhVien = new SinhVien(txtten.getText().toString(), spinner.getSelectedItem().toString(), txtsubject.getText().toString());

                    long check = db.insert(sinhVien);
                    if(check == -1)
                        Toast.makeText(MainActivity.this,  "Insert khong thanh cong", Toast.LENGTH_SHORT).show();
                    loadDB();
                }
            }
        });
        btnsuasv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SinhVien sinhVien = new SinhVien(Integer.parseInt(tvid.getText().toString()), txtten2.getText().toString(), txtsubject2.getText().toString());
                int check = db.update(sinhVien);
                if (check != 1){
                    Toast.makeText(MainActivity.this, "Update khong thanh cong", Toast.LENGTH_SHORT).show();
                }
                loadDB();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayList.get(i) + "", Toast.LENGTH_SHORT).show();
                SinhVien sinhVien = (SinhVien) arrayList.get(i);
                tvid.setText(String.valueOf(sinhVien.getId()));
                txtten2.setText(sinhVien.getName());
                txtsubject2.setText(String.valueOf(sinhVien.getSubject()));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                SinhVien sinhVien = (SinhVien) arrayList.get(i);
                showdialog(sinhVien);
                return true;
            }
        });

    }
    public void loadDB(){
        arrayList.clear();
        arrayAdapterspiner.clear();
        Cursor cs = db.queryCurso("select * from SinhVien");
        Cursor cslop = db.queryCurso("select * from Lop");
        while (cs.moveToNext()){
            SinhVien sinhVien = new SinhVien(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3));
            arrayList.add(sinhVien);
        }
        listView.setAdapter(adapter);

        while (cslop.moveToNext()){
            Lop lop = new Lop(cslop.getInt(0), cslop.getString(1));
            arrayListLop.add(lop);
        }
        arrayAdapterspiner.setDropDownViewResource( android.R.layout.simple_list_item_single_choice );
        spinner.setAdapter( arrayAdapterspiner );

        adapter.notifyDataSetChanged();
        Toast.makeText(this, arrayList.size() + "ok", Toast.LENGTH_SHORT).show();
    }
    public void showdialog(final SinhVien sinhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xóa sinh vien");
        builder.setMessage("Bạn muốn xoa sinh viên này không ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.delete(sinhVien);
                Toast.makeText(MainActivity.this, "Delete ok ", Toast.LENGTH_SHORT).show();
                loadDB();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
