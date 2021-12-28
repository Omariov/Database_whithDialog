package com.example.mydb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nom,ville;
    Button insert, afficher;
    com.example.mydb.DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom=findViewById(R.id.nom);
        ville=findViewById(R.id.ville);
        insert=findViewById(R.id.inserer);
        afficher=findViewById(R.id.afficher);
        db=new com.example.mydb.DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomtxt=nom.getText().toString();
                String villetxt=ville.getText().toString();

                Boolean checkinsert = db.insertuserdata(nomtxt,villetxt);
                if(checkinsert==true) {
                    Toast.makeText(MainActivity.this, "insertion valide", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "insertion non valide", Toast.LENGTH_LONG).show();
                }


            }
        });

        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "no valeur exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("nom :"+res.getString(0)+"\n");
                    buffer.append("ville :"+res.getString(1)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("les donneés de MYdb");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });


    }
}