package com.esau.poliagenda.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.R;

public class viewHolderMaterias extends  RecyclerView.ViewHolder {
    View mView;
    private viewHolderMaterias.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(viewHolderMaterias.ClickListener clickListener) {
        mClickListener = clickListener;
    }

    public viewHolderMaterias(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });


    }

    public  void setearDatosMaterias(String nombre, String profesor, String autor,String editorial,String titulo,String secuenciat) {

        TextView nombreMateria, profesorMateria, autorReferencia, editorialReferencia, tituloReferencia,secuencia,secuenciaData;

        nombreMateria = mView.findViewById(R.id.listUnidadAprendizaje);
        profesorMateria = mView.findViewById(R.id.listprofesor);
        autorReferencia = mView.findViewById(R.id.listautor);
        editorialReferencia = mView.findViewById(R.id.listeditorial);
        tituloReferencia = mView.findViewById(R.id.listTitulo);
        secuencia = mView.findViewById(R.id.listSecuencia);
        secuenciaData = mView.findViewById(R.id.secuenciaData);

        nombreMateria.setText(nombre);
        profesorMateria.setText(profesor);
        autorReferencia.setText(autor);
        editorialReferencia.setText(editorial);
        tituloReferencia.setText(titulo);
        secuencia.setText("Ver más");

        secuenciaData.setText(secuenciat);
    }
}



