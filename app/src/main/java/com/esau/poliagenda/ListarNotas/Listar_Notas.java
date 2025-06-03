package com.esau.poliagenda.ListarNotas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esau.poliagenda.ActualizarNota.Actualizar_Nota;
import com.esau.poliagenda.AgregarNotas.Agregar_Nota;
import com.esau.poliagenda.Detalle.Detalle_Nota;
import com.esau.poliagenda.Objetos.Nota;
import com.esau.poliagenda.R;
import com.esau.poliagenda.ViewHolder.ViewHolder_Nota;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Listar_Notas extends AppCompatActivity {

    Button btnAgregarNota;
    RecyclerView recyclerViewNotas;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference BASE_DE_DATOS;

    LinearLayoutManager linearLayoutManager;

    FirebaseRecyclerAdapter<Nota, ViewHolder_Nota> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Nota> options;

    Dialog dialog;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar_notas);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Mis notas");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAgregarNota=findViewById(R.id.btnAgregarNota);
        recyclerViewNotas=findViewById(R.id.recyclerviewNotas);
        recyclerViewNotas.setHasFixedSize(true);


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();

        firebaseDatabase=FirebaseDatabase.getInstance();
        BASE_DE_DATOS=firebaseDatabase.getReference("Notas_Publicadas");
        dialog=new Dialog(Listar_Notas.this);
        ListarNotasUsuarios();

        btnAgregarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uid_usuario = user.getUid();
                String correo_usuario = user.getEmail();
                Intent intent = new Intent(Listar_Notas.this,Agregar_Nota.class);
                intent.putExtra("Uid",uid_usuario);
                intent.putExtra("Correo",correo_usuario);
                startActivity(intent);
            }
        });

    }

    private void ListarNotasUsuarios(){
        //Consulta
        Query query=BASE_DE_DATOS.orderByChild("uid_usuario").equalTo(user.getUid());
        options=new FirebaseRecyclerOptions.Builder<Nota>().setQuery(query,Nota.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Nota, ViewHolder_Nota>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Nota viewHolderNota, int i, @NonNull Nota nota) {
                viewHolderNota.SetearDatos(
                        getApplicationContext(),
                        nota.getId_nota(),
                        nota.getUid_usuario(),
                        nota.getCorreo_usuario(),
                        nota.getFecha_hora_actual(),
                        nota.getTitulo(),
                        nota.getDescripcion(),
                        nota.getFecha_nota(),
                        nota.getEstado()
                );
            }

            @Override
            public ViewHolder_Nota onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota,parent,false);
                ViewHolder_Nota viewHolderNota=new ViewHolder_Nota(view);
                viewHolderNota.setOnClickListener(new ViewHolder_Nota.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Obtener los datos de la nota seleccionada
                        String id_nota=getItem(position).getId_nota();
                        String uid_usuario=getItem(position).getUid_usuario();
                        String correo_usuario=getItem(position).getCorreo_usuario();
                        String fecha_registro=getItem(position).getFecha_hora_actual();
                        String titulo=getItem(position).getTitulo();
                        String descripcion=getItem(position).getDescripcion();
                        String fecha_nota=getItem(position).getFecha_nota();
                        String estado=getItem(position).getEstado();

                        //Enviar datos
                        Intent intent=new Intent(Listar_Notas.this, Detalle_Nota.class);
                        intent.putExtra("id_nota", id_nota);
                        intent.putExtra("uid_usuario", uid_usuario);
                        intent.putExtra("correo_usuario", correo_usuario);
                        intent.putExtra("fecha_registro", fecha_registro);
                        intent.putExtra("titulo", titulo);
                        intent.putExtra("descripcion", descripcion);
                        intent.putExtra("fecha_nota", fecha_nota);
                        intent.putExtra("estado", estado);
                        startActivity(intent);

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        //Obtener los datos de la nota seleccionada
                        String id_nota=getItem(position).getId_nota();
                        String uid_usuario=getItem(position).getUid_usuario();
                        String correo_usuario=getItem(position).getCorreo_usuario();
                        String fecha_registro=getItem(position).getFecha_hora_actual();
                        String titulo=getItem(position).getTitulo();
                        String descripcion=getItem(position).getDescripcion();
                        String fecha_nota=getItem(position).getFecha_nota();
                        String estado=getItem(position).getEstado();

                        //Declarar las vistas
                        Button CD_Eliminar, CD_Actualizar;

                        //Declarar la conexcion con el diseño
                        dialog.setContentView(R.layout.dialogo_opciones);

                        //Inicializar las vistas
                        CD_Eliminar=dialog.findViewById(R.id.CD_Eliminar);
                        CD_Actualizar=dialog.findViewById(R.id.CD_Actualiazr);

                        CD_Eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EliminarNota(id_nota);
                                dialog.dismiss();
                            }
                        });

                        CD_Actualizar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //startActivity(new Intent(Listar_Notas.this, Actualizar_Nota.class));
                                //Toast.makeText(Listar_Notas.this, "Actualizar nota", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Listar_Notas.this, Actualizar_Nota.class);
                                intent.putExtra("id_nota", id_nota);
                                intent.putExtra("uid_usuario", uid_usuario);
                                intent.putExtra("correo_usuario", correo_usuario);
                                intent.putExtra("fecha_registro", fecha_registro);
                                intent.putExtra("titulo", titulo);
                                intent.putExtra("descripcion", descripcion);
                                intent.putExtra("fecha_nota", fecha_nota);
                                intent.putExtra("estado", estado);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        });

                        dialog.show();
                    }
                });
                return viewHolderNota;
            }
        };
        linearLayoutManager=new LinearLayoutManager(Listar_Notas.this, LinearLayoutManager.VERTICAL,false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerViewNotas.setLayoutManager(linearLayoutManager);
        recyclerViewNotas.setAdapter(firebaseRecyclerAdapter);
    }

    private void EliminarNota(String idNota) {
        AlertDialog.Builder builder=new AlertDialog.Builder(Listar_Notas.this);
        builder.setTitle("Eliminar nota");
        builder.setMessage("¿Desea eliminar la nota?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Query query=BASE_DE_DATOS.orderByChild("id_nota").equalTo(idNota);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds:snapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                        Toast.makeText(Listar_Notas.this, "Nota eliminada", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Listar_Notas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Listar_Notas.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter!=null){
            firebaseRecyclerAdapter.startListening();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}