package com.esau.poliagenda.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.R;

public class ViewHorlderContacto extends RecyclerView.ViewHolder {
    View mView;

    private ViewHorlderContacto.ClickListener mClickListener;


    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);

    }

    public void setOnClickListener(ViewHorlderContacto.ClickListener clickListener){
        mClickListener=clickListener;
    }

    public ViewHorlderContacto(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return false;
            }
        });
    }

    public void SetearDatosContacto(Context context,
                                    String  id_contacto,
                                    String uid_contacto,
                                    String nombres,
                                    String apellidos,
                                    String correo,
                                    String telefono,
                                    String edad,
                                    String direccion,
                                    String imagen){
        ImageView Imagen_c_Item;
        TextView Id_c_Item, Uid_c_Item, nombres_c_Item, apellidos_c_Item, correo_c_Item, telefono_c_Item, edad_c_Item, direccion_c_Item;

        Imagen_c_Item=mView.findViewById(R.id.Imagen_c_Item);
        Id_c_Item=mView.findViewById(R.id.Id_c_Item);
        Uid_c_Item=mView.findViewById(R.id.Uid_c_Item);
        nombres_c_Item=mView.findViewById(R.id.nombres_c_Item);
        apellidos_c_Item=mView.findViewById(R.id.apellidos_c_Item);
        correo_c_Item=mView.findViewById(R.id.correo_c_Item);
        telefono_c_Item=mView.findViewById(R.id.telefono_c_Item);
        edad_c_Item=mView.findViewById(R.id.edad_c_Item);
        direccion_c_Item=mView.findViewById(R.id.direccion_c_Item);

        Id_c_Item.setText(id_contacto);
        Uid_c_Item.setText(uid_contacto);
        nombres_c_Item.setText(nombres);
        apellidos_c_Item.setText(apellidos);
        correo_c_Item.setText(correo);
        telefono_c_Item.setText(telefono);
        edad_c_Item.setText(edad);
        direccion_c_Item.setText(direccion);

    }
}
