package com.esau.poliagenda.Listar_referencias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;

import com.esau.poliagenda.Objetos.Materia.Materia;
import com.esau.poliagenda.Objetos.Nota;
import com.esau.poliagenda.R;
import com.esau.poliagenda.ViewHolder.viewHolderMaterias;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Listar_referencias extends AppCompatActivity {
    RecyclerView recyclerViewReferencias;
    LinearLayoutManager linearLayoutManager;
    private  static final String TAG = "FirebaseConexion";
    FirebaseDatabase firebaseDatabase;

    DatabaseReference BASE_DATOS_MATERIAS;


    FirebaseAuth auth;
    FirebaseUser user;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_referencias);
        recyclerViewReferencias = findViewById(R.id.recyclerViewReferencias);
        recyclerViewReferencias.setHasFixedSize(true);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        BASE_DATOS_MATERIAS = firebaseDatabase.getReference("Materias");
        Listar_Materias();
        dialog = new Dialog(Listar_referencias.this);
    }

    private void Listar_Materias() {
        BASE_DATOS_MATERIAS.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Materia> materiasList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convertir cada hijo del nodo a un Map o a tu clase modelo
                    for(DataSnapshot materia:snapshot.getChildren() ){
                        Materia materia1=materia.getValue(Materia.class);
                        if (materia1 != null) {
                            materiasList.add(materia1);
                        } else {
                            Log.e(TAG, "Materia is null for snapshot: " + materia.getKey());
                        }
                    }
                }
                 linearLayoutManager = new LinearLayoutManager(Listar_referencias.this, LinearLayoutManager.VERTICAL, false);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerViewReferencias.setLayoutManager(linearLayoutManager);
                recyclerViewReferencias.setAdapter(new MateriaAdapterBibliografia(materiasList));


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("xd", "Error al leer datos: " + databaseError.getMessage());
            }
        });
    }

}