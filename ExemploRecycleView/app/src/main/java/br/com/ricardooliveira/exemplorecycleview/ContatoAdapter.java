package br.com.ricardooliveira.exemplorecycleview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private Context context;
    private List<Contato> contatos;

    public ContatoAdapter(MainActivity context, List<Contato> contatos) {
        this.context = context;
        this.contatos = contatos;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.context)
                .inflate(R.layout.recyclerview_contato, parent, false);

        ContatoViewHolder contatoViewHolder = new ContatoViewHolder(v);

        return contatoViewHolder;
    }

    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
        Contato contato = this.contatos.get(position);

        holder.txtNome.setText(contato.getNome());
        holder.txtTelefone.setText(contato.getTelefone());
        holder.imgContato.setImageResource(contato.getImagem());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ContatoViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imgContato;
        TextView txtNome;
        TextView txtTelefone;
        public ContatoViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imgContato = (ImageView) itemView.findViewById(R.id.imgContato);
            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
        }
    }

}
