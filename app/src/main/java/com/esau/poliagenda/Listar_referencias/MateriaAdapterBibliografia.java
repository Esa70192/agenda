package com.esau.poliagenda.Listar_referencias;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.DetalleReferencia.DetalleReferencia;
import com.esau.poliagenda.Objetos.Materia.Materia;
import com.esau.poliagenda.R;
import com.esau.poliagenda.ViewHolder.viewHolderMaterias;

import java.util.List;

public class MateriaAdapterBibliografia extends RecyclerView.Adapter<viewHolderMaterias> {
    private List<Materia> materiasList;

    public  MateriaAdapterBibliografia(List<Materia> materiasList) {
        this.materiasList = materiasList;
        Log.d("MateriaAdapter", "Adapter initialized with " + materiasList.size() + " items.");
    }


    @Override
    public viewHolderMaterias onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_referencias, parent, false);
        return new viewHolderMaterias(view);
    }

    @Override
    public void onBindViewHolder(viewHolderMaterias holder, int position) {
        Materia m = materiasList.get(position);

        holder.setearDatosMaterias(
                m.getNombre(),
                m.getProfesor(),
                m.getReferencias().getAutor(),
                m.getReferencias().getEditorial(),
                m.getReferencias().getTitulo(),
                m.getSecuencia()
        );

        holder.setOnClickListener(new viewHolderMaterias.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetalleReferencia.class);
                intent.putExtra("nombre", m.getNombre());
                intent.putExtra("profesor", m.getProfesor());
                intent.putExtra("autor", m.getReferencias().getAutor());
                intent.putExtra("editorial", m.getReferencias().getEditorial());
                intent.putExtra("titulo", m.getReferencias().getTitulo());
                intent.putExtra("secuencia", m.getSecuencia());
                context.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public int getItemCount() {
        // Return the total number of items
        return materiasList.size(); // Replace with actual implementation
    }
}
