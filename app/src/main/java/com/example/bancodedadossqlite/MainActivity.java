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
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

            //atualizados dados

            //String atualizar = "UPDATE pessoas SET idade=19 WHERE NOME='Eduarda'";
            //bancoDados.execSQL(atualizar);

            //String excluir = "DELETE FROM pessoas WHERE ID=2";
            //bancoDados.execSQL(excluir);

            //apagar a tabela
            //bancoDados.execSQL("DROP TABLE pessoas");

            //Inserir dados
            //bancoDados.execSQL("INSERT INTO Pessoas(nome, idade) values ('Lucas',7) ");
            //bancoDados.execSQL("INSERT INTO Pessoas(nome, idade) values ('Eduarda',11) ");

            //Recuperar pessoas
            String consulta = "SELECT id, nome, idade FROM pessoas WHERE 1=1 ORDER BY NOME ASC";
            Cursor cursor =  bancoDados.rawQuery(consulta,null);

            //Indices da tabela
            int indiceId = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null) {

                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                Log.i("RESULTADO - ", id + "/nome: " +  nome + "/ Idade: " + idade);
                cursor.moveToNext();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
