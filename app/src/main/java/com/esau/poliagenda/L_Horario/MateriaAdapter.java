package com.esau.poliagenda.L_Horario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.R;

import java.util.List;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.ViewHolder> {
    private List<Materia> lista;

    public MateriaAdapter(List<Materia> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvSecuencia, tvProfesor, tvHorario1, tvHorario2;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvSecuencia = itemView.findViewById(R.id.tvSecuencia);
            tvProfesor = itemView.findViewById(R.id.tvProfesor);
            tvHorario1 = itemView.findViewById(R.id.tvHorario1);
            tvHorario2 = itemView.findViewById(R.id.tvHorario2);
        }
    }

    @Override
    public MateriaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MateriaAdapter.ViewHolder holder, int position) {
        Materia m = lista.get(position);
        holder.tvNombre.setText("Nombre: " + m.Nombre);
        holder.tvSecuencia.setText("Secuencia: " + m.Secuencia);
        holder.tvProfesor.setText("Profesor: " + m.Profesor);
        holder.tvHorario1.setText("Horario 1: " + m.Horario1.dia + " - " + m.Horario1.hora);
        holder.tvHorario2.setText("Horario 2: " + m.Horario2.dia + " - " + m.Horario2.hora);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
