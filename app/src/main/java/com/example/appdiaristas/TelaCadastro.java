package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaCadastro extends AppCompatActivity {

    private EditText editTextNomeCadastro;
    private EditText editTextTelefoneCadastro;
    private Spinner spinnerTipoTelefone;
    private Button buttonSalvarCadastro;
    private Button buttonAdicionarTelefone;
    private LinearLayout layoutTelefones;
    private List<Telefone> listaTelefones;
    private Contato contato; // Variável para armazenar o contato sendo editado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        editTextNomeCadastro = findViewById(R.id.editTextNomeCadastro);
        editTextTelefoneCadastro = findViewById(R.id.editTextTelefoneCadastro);
        spinnerTipoTelefone = findViewById(R.id.spinnerTipoTelefone);
        buttonSalvarCadastro = findViewById(R.id.buttonSalvarCadastro);
        buttonAdicionarTelefone = findViewById(R.id.buttonAdicionarTelefone);
        layoutTelefones = findViewById(R.id.layoutTelefones);
        listaTelefones = new ArrayList<>();

        // Verificar se foi passado um contato para edição
        contato = getIntent().getParcelableExtra("contato");
        if (contato != null) {
            preencherCamposEdicao();
        }

        buttonSalvarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarContato();
            }
        });

        buttonAdicionarTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarTelefone();
            }
        });
    }

    private void preencherCamposEdicao() {
        editTextNomeCadastro.setText(contato.getNome());
        listaTelefones = contato.getTelefones();
        exibirTelefones();
    }

    private void salvarContato() {
        String nome = editTextNomeCadastro.getText().toString();
        if (nome.isEmpty()) {
            Toast.makeText(this, "Informe o nome completo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (listaTelefones.isEmpty()) {
            Toast.makeText(this, "Adicione pelo menos um telefone", Toast.LENGTH_SHORT).show();
            return;
        }

        // Atualizar o contato existente ou criar um novo contato com as informações preenchidas
        if (contato == null) {
            contato = new Contato(nome, listaTelefones);
        } else {
            contato.setNome(nome);
            contato.setTelefones(listaTelefones);
        }

        // Retornar o contato atualizado para a tela anterior
        Intent resultIntent = new Intent();
        resultIntent.putExtra("contato", contato);
        setResult(Activity.RESULT_OK, resultIntent);
        finish(); // Voltar para a tela anterior
    }

    private void adicionarTelefone() {
        String numero = editTextTelefoneCadastro.getText().toString();
        String tipo = spinnerTipoTelefone.getSelectedItem().toString();

        if (numero.isEmpty()) {
            Toast.makeText(this, "Informe o número de telefone", Toast.LENGTH_SHORT).show();
            return;
        }

        Telefone telefone = new Telefone(numero, tipo);
        listaTelefones.add(telefone);
        exibirTelefones();
        limparCamposTelefone();
    }

    private void exibirTelefones() {
        layoutTelefones.removeAllViews();

        for (Telefone telefone : listaTelefones) {
            View telefoneView = getLayoutInflater().inflate(R.layout.item_telefone, null);
            TextView textViewNumero = telefoneView.findViewById(R.id.textViewNumero);
            TextView textViewTipo = telefoneView.findViewById(R.id.textViewTipo);

            textViewNumero.setText(telefone.getNumero());
            textViewTipo.setText(telefone.getTipo());

            layoutTelefones.addView(telefoneView);
        }
    }

    private void limparCamposTelefone() {
        editTextTelefoneCadastro.setText("");
        spinnerTipoTelefone.setSelection(0);
    }
}
