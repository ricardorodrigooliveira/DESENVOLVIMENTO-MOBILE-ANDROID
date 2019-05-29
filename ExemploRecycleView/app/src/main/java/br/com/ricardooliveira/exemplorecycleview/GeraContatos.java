package br.com.ricardooliveira.exemplorecycleview;

import java.util.ArrayList;
import java.util.List;

public class GeraContatos {

    public static List<Contato> getContatos(){
        List<Contato> contatos = new ArrayList<>();

        contatos.add(new Contato("João", "(11) 1234-5678", "Ocupado", R.drawable.p1));
        contatos.add(new Contato("Marcia", "(11) 2345-6789", "Disponível", R.drawable.p2));
        contatos.add(new Contato("Enzo", "(11) 3456-7890", "No Trabalho", R.drawable.p1));
        contatos.add(new Contato("Carla", "(11) 4567-8901", "Ocupado", R.drawable.p1));

        return contatos;
    }
}
