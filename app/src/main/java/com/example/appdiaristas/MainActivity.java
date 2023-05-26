package com.example.appdiaristas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewContatos;
    private List<String> listaContatos;
    private ArrayAdapter<String> adapter;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewContatos = findViewById(R.id.listViewContatos);
        listaContatos = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaContatos);
        listViewContatos.setAdapter(adapter);

        abrirBanco();
        carregarContatos();
        fecharDB();
    }

    public void abrirBanco() {
        try {
            db = openOrCreateDatabase("BancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            CxMsg.mostrar("Erro ao abrir ou ao criar o banco", this);
        }
    }

    public void fecharDB() {
        db.close();
    }

    public void carregarContatos() {
        listaContatos.clear();

        Cursor cursor = db.rawQuery("SELECT nome FROM contatos ORDER BY nome ASC", null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("nome");
            do {
                String nome = cursor.getString(columnIndex);
                listaContatos.add(nome);
            } while (cursor.moveToNext());
        }

        // Atualize o adapter do ListView
        adapter.notifyDataSetChanged();
    }


    // Restante do código...

    // Métodos existentes como inserirRegistro, abrirTela_Consulta, FecharTela, etc.
}
