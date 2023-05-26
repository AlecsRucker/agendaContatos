package com.example.appdiaristas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContatoAdapter extends BaseAdapter {
    private Context context;
    private List<Contato> listaContatos;

    public ContatoAdapter(Context context, List<Contato> listaContatos) {
        this.context = context;
        this.listaContatos = listaContatos;
    }

    @Override
    public int getCount() {
        return listaContatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaContatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false);

            holder = new ViewHolder();
            holder.textViewNome = convertView.findViewById(R.id.textViewNome);
            holder.textViewTelefone = convertView.findViewById(R.id.textViewTelefone);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contato contato = listaContatos.get(position);
        holder.textViewNome.setText(contato.getNome());

        // Verifique se o contato possui telefones
        if (contato.getTelefones() != null && !contato.getTelefones().isEmpty()) {
            Telefone primeiroTelefone = contato.getTelefones(0); // Obtém o primeiro telefone
            if (primeiroTelefone != null) {
                holder.textViewTelefone.setText(primeiroTelefone.getNumero());
            } else {
                holder.textViewTelefone.setText(""); // Defina um valor padrão caso o telefone seja nulo
            }
        } else {
            holder.textViewTelefone.setText(""); // Defina um valor padrão caso não haja telefones
        }

        return convertView;
    }



    private static class ViewHolder {
        TextView textViewNome;
        TextView textViewTelefone;
    }
}

