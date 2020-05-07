package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            //cria banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app",MODE_PRIVATE, null); // apenas o app acesso o banco

            //criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (nome VARCHAR, idade INT(3))");

            //Inserir dados
            bancoDados.execSQL("INSERT INTO Pessoas(nome, idade) values ('Fernando',40) ");
            bancoDados.execSQL("INSERT INTO Pessoas(nome, idade) values ('Graziele',35) ");

            //Recuperar pessoas
            Cursor cursor =  bancoDados.rawQuery("SELECT nome, idade FROM pessoas",null);

            //Indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null) {
                Log.i("RESULTADO - NOME: ", cursor.getString(indiceNome));
                Log.i("RESULTADO - IDADE: ", cursor.getString(indiceIdade));
                cursor.moveToNext();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
