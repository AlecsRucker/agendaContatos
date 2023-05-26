package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class TelaListagemContatos extends AppCompatActivity {

    private ListView listViewContatos;
    private Button buttonNovoContato;
    private List<Contato> listaContatos;
    private ContatoAdapter contatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_listagem_contatos);

        listViewContatos = findViewById(R.id.listViewContatos);
        buttonNovoContato = findViewById(R.id.buttonNovoContato);
        listaContatos = new ArrayList<>();

        // Exemplo: preenchendo a lista de contatos com dados fictícios
        listaContatos.add(new Contato("João", "123456789"));
        listaContatos.add(new Contato("Maria", "987654321"));
        listaContatos.add(new Contato("Pedro", "456789123"));

        contatoAdapter = new ContatoAdapter(this, listaContatos);
        listViewContatos.setAdapter(contatoAdapter);

        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abrirTelaEdicaoContato(position);
            }
        });

        buttonNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirTelaCadastroContato();
            }
        });
    }

    private void abrirTelaEdicaoContato(int position) {
        Contato contatoSelecionado = listaContatos.get(position);

        Intent intent = new Intent(this, TelaEdicaoContato.class);
        intent.putExtra("contato", contatoSelecionado);
        startActivity(intent);
    }



    private void abrirTelaCadastroContato() {
        // Abrir a tela de cadastro de contato
        // Implemente essa parte de acordo com o tópico 2 do trabalho
    }
}
