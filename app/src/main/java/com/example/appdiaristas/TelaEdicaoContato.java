package com.example.appdiaristas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaEdicaoContato extends AppCompatActivity {

    private EditText editTextNomeEdicao;
    private Button buttonSalvarEdicao;
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_edicao_contato);

        editTextNomeEdicao = findViewById(R.id.editTextNomeEdicao);
        buttonSalvarEdicao = findViewById(R.id.buttonSalvarEdicao);

        contato = (Contato) getIntent().getSerializableExtra("contato");

        if (contato != null) {
            editTextNomeEdicao.setText(contato.getNome());
        }

        buttonSalvarEdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEdicaoContato();
            }
        });
    }

    private void salvarEdicaoContato() {
        String novoNome = editTextNomeEdicao.getText().toString();

        if (novoNome.isEmpty()) {
            Toast.makeText(this, "Informe o nome completo", Toast.LENGTH_SHORT).show();
            return;
        }

        if (contato != null) {
            // Atualizar o contato no banco de dados ou em outra estrutura de dados
            Toast.makeText(this, "Contato atualizado com sucesso", Toast.LENGTH_SHORT).show();
            finish(); // Voltar para a tela anterior
        }
    }
}

