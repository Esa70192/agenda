package com.esau.poliagenda.Listar_referencias;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.esau.poliagenda.Objetos.Materia.Materia;
import com.esau.poliagenda.R;
import com.esau.poliagenda.ViewHolder.viewHolderMaterias;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Listar_referencias extends AppCompatActivity {
    RecyclerView recyclerViewReferencias;
    private  static final String TAG = "FirebaseConexion";
    FirebaseDatabase firebaseDatabase;

    DatabaseReference BASE_DATOS_MATERIAS;



    FirebaseRecyclerAdapter <Materia, viewHolderMaterias> firebaseRecyclerAdapter;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_referencias);
        //recyclerViewReferencias = findViewById(R.id.recyclerViewReferencias);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        BASE_DATOS_MATERIAS = firebaseDatabase.getReference("Materias");
        Listar_Materias();
    }

}