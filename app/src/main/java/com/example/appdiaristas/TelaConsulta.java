package com.example.appdiaristas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TelaConsulta extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_anterior, btn_proximo, btn_voltar;
    SQLiteDatabase db = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        et_nome=(EditText)findViewById(R.id.editTextNomeConsulta);
        et_telefone=(EditText)findViewById(R.id.editTextTelefoneConsulta);
        btn_anterior=(Button) findViewById(R.id.buttonAnteriorConsulta);
        btn_proximo=(Button) findViewById(R.id.buttonProximoConsulta);
        btn_voltar=(Button) findViewById(R.id.buttonVoltarConsulta);

        buscarDados();
    }
    public void FecharTela_Consulta(View v){
        this.finish();
    }

    public void abrirBanco() {
        try {
            db = openOrCreateDatabase("BancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            CxMsg.mostrar("Erro ao abrir ou ao criar o banco", this);
        }
    }
    public void fecharDB(){

        db.close();
    }

    public void buscarDados(){
        abrirBanco();
        cursor=db.query("contatos",
                new String[]{"nome","fone"},
                null,
                null,
                null,
                null,
                null,
            null
        );
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            mostrarDados();

        }else{
            CxMsg.mostrar("Nenhum registro encontrado", this);

        }
    }

    public void proximoRegistro(View v) {
        try {
            cursor.moveToNext();
            mostrarDados();
        } catch (Exception ex) {
            if(cursor.isAfterLast()) {
                CxMsg.mostrar("Não existem mais registros", this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros", this);
            }
        }
    }

    public void registroAnterior(View v) {
        try {
            cursor.moveToPrevious();
            mostrarDados();
        } catch (Exception ex) {
            if(cursor.isBeforeFirst()) {
                CxMsg.mostrar("Não existem mais registros", this);
            }else{
                CxMsg.mostrar("Erro ao navegar pelos registros", this);
            }
        }
    }

    @SuppressLint("Range")
    public void mostrarDados(){
        et_nome.setText(cursor.getString(cursor.getColumnIndex("nome")));
        et_telefone.setText(cursor.getString(cursor.getColumnIndex("fone")));

    }

}
