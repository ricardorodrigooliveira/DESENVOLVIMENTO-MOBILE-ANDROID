package br.com.fiap.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContatoAdapter extends BaseAdapter {

    private Context context;
    private Contato[] contatos;

    public ContatoAdapter(Context context, Contato[] contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
        return this.contatos.length;
    }

    @Override
    public Object getItem(int position) {
        return this.contatos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(this.context);

        View v = inflater.inflate(R.layout.listview_contato, null);

        TextView txtNome = v.findViewById(R.id.txtNome);
        TextView txtTelefone = v.findViewById(R.id.txtTelefone);
        ImageView imgContato = v.findViewById(R.id.imgContato);

        Contato contato = this.contatos[position];
        txtNome.setText( contato.getNome() );
        txtTelefone.setText( contato.getTelefone() );
        imgContato.setImageResource( contato.getImagem() );

        return v;
    }
}
