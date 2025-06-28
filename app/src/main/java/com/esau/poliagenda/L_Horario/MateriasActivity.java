package com.esau.poliagenda.L_Horario;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MateriasActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private List<Materia> listaMaterias = new ArrayList<>();
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);

        spinner = findViewById(R.id.spinnerSemestres);
        recyclerView = findViewById(R.id.recyclerMaterias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Horario");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        dbRef = FirebaseDatabase.getInstance().getReference("Materias");

        // Cargar semestres
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> semestres = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    semestres.add(ds.getKey());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MateriasActivity.this,
                        android.R.layout.simple_spinner_item, semestres);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String semestreSeleccionado = semestres.get(position);
                        cargarMaterias(semestreSeleccionado);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    private void cargarMaterias(String semestre) {
        dbRef.child(semestre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaMaterias.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String secuencia = ds.child("Secuencia").getValue(String.class);
                    String nombre = ds.child("Nombre").getValue(String.class);
                    String profesor = ds.child("Profesor").getValue(String.class);

                    Horario h1 = ds.child("Horario1").getValue(Horario.class);
                    Horario h2 = ds.child("Horario2").getValue(Horario.class);

                    Materia m = new Materia(secuencia, nombre, profesor, h1, h2);
                    listaMaterias.add(m);
                }

                recyclerView.setAdapter(new MateriaAdapter(listaMaterias));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}

