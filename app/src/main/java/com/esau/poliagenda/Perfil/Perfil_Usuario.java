package com.esau.poliagenda.Perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esau.poliagenda.MenuPrinciapl;
import com.esau.poliagenda.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Perfil_Usuario extends AppCompatActivity {

    ImageView Imagen_Perfil;
    TextView Correo_Perfil, Uid_Perfil;
    EditText Nombres_Perfil, Apellidos_Perfil, Edad_Perfil, Telefono_Perfil, Domicilio_Perfil, Universidad_Perfil, Profresion_Perfil;
    Button Guardar_Datos;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    DatabaseReference Usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Perfil de usuario");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        InicializarVaribales();

        Guardar_Datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActualizarDatos();
            }
        });
    }

    private void InicializarVaribales(){
        Imagen_Perfil=findViewById(R.id.Imagen_Perfil);
        Correo_Perfil = findViewById(R.id.Correo_Perfil);
        Uid_Perfil = findViewById(R.id.Uid_Perfil);
        Nombres_Perfil = findViewById(R.id.Nombres_Perfil);
        Apellidos_Perfil = findViewById(R.id.Apellidos_Perfil);
        Edad_Perfil = findViewById(R.id.Edad_Perfil);
        Telefono_Perfil = findViewById(R.id.Telefono_Perfil);
        Domicilio_Perfil = findViewById(R.id.Domicilio_Perfil);
        Universidad_Perfil = findViewById(R.id.Universidad_Perfil);
        Profresion_Perfil = findViewById(R.id.Profresion_Perfil);

        Guardar_Datos = findViewById(R.id.Guardar_Datos);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        Usuarios = FirebaseDatabase.getInstance().getReference("Usuarios");
    }

    private void LecturaDeDatos(){
        Usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //Obtener datos
                    String uid=""+snapshot.child("uid").getValue();
                    String nombre = ""+snapshot.child("nombre").getValue();
                    String apellidos = ""+snapshot.child("apellidos").getValue();
                    String correo = ""+snapshot.child("Correo").getValue();
                    String edad = ""+snapshot.child("edad").getValue();
                    String telefono = ""+snapshot.child("telefono").getValue();
                    String domicilio = ""+snapshot.child("domicilio").getValue();
                    String universidad = ""+snapshot.child("universidad").getValue();
                    String profesion = ""+snapshot.child("profesion").getValue();

                    //Vistas
                    Uid_Perfil.setText(uid);
                    Nombres_Perfil.setText(nombre);
                    Apellidos_Perfil.setText(apellidos);
                    Correo_Perfil.setText(correo);
                    Edad_Perfil.setText(edad);
                    Telefono_Perfil.setText(telefono);
                    Domicilio_Perfil.setText(domicilio);
                    Universidad_Perfil.setText(universidad);
                    Profresion_Perfil.setText(profesion);
                }else{
                    Toast.makeText(Perfil_Usuario.this, "Esta esperando datos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Perfil_Usuario.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ActualizarDatos(){
        String A_Nombre=Nombres_Perfil.getText().toString().trim();
        String A_Apellidos=Apellidos_Perfil.getText().toString().trim();
        String A_Edad=Edad_Perfil.getText().toString().trim();
        String A_Telefono=Telefono_Perfil.getText().toString().trim();
        String A_Domicilio=Domicilio_Perfil.getText().toString().trim();
        String A_Universidad=Universidad_Perfil.getText().toString().trim();
        String A_Profesion=Profresion_Perfil.getText().toString().trim();

        HashMap<String, Object> Datos_Actualizazr=new HashMap<>();

        Datos_Actualizazr.put("nombre",A_Nombre);
        Datos_Actualizazr.put("apellidos",A_Apellidos);
        Datos_Actualizazr.put("edad",A_Edad);
        Datos_Actualizazr.put("telefono",A_Telefono);
        Datos_Actualizazr.put("domicilio",A_Domicilio);
        Datos_Actualizazr.put("universidad",A_Universidad);
        Datos_Actualizazr.put("profesion",A_Profesion);

        Usuarios.child(user.getUid()).updateChildren(Datos_Actualizazr)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Perfil_Usuario.this, "Acualizado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Perfil_Usuario.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void ComprobarInicioSesion(){
        if(user!=null){
            LecturaDeDatos();
        }else{
            startActivity(new Intent(Perfil_Usuario.this, MenuPrinciapl.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}