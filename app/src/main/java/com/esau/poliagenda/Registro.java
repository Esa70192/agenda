package com.esau.poliagenda;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText NombreET, CorreoET, ContraseñaET, ConfirmarContraseñaET;
    Button RegistrarUsuraio;
    TextView TengounacuentaTXT;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    //
    String nombre=" ",correo=" ",contra="",confirmarcontra="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Registar");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        NombreET=findViewById(R.id.NombreET);
        CorreoET=findViewById(R.id.CorreoET);
        ContraseñaET=findViewById(R.id.ContraseñaET);
        ConfirmarContraseñaET=findViewById(R.id.ConfirmarContraseñaET);
        RegistrarUsuraio=findViewById(R.id.RegistrarUsuario);
        TengounacuentaTXT=findViewById(R.id.TengounacuentaTXT);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(Registro.this);
        progressDialog.setTitle("Espero por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        RegistrarUsuraio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validardatos();
            }
        });

        TengounacuentaTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this, Login.class));
            }
        });

    }
    private void validardatos(){
        nombre=NombreET.getText().toString();
        correo=CorreoET.getText().toString();
        contra=ContraseñaET.getText().toString();
        confirmarcontra=ConfirmarContraseñaET.getText().toString();

        if(TextUtils.isEmpty(nombre)){
            Toast.makeText(this, "Ingrese nombre", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Ingrese correo", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(contra)) {
            Toast.makeText(this, "Ingrese contraseña", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmarcontra)) {
            Toast.makeText(this, "Confirme contraseña", Toast.LENGTH_SHORT).show();
        } else if (!contra.equals(confirmarcontra)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else{
            CrearCuenta();
        }
    }

    private void CrearCuenta() {
        progressDialog.setMessage("Creando su cuenta");
        progressDialog.show();

        //Crear un usuario en firebase
        firebaseAuth.createUserWithEmailAndPassword(correo,contra)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        GuardarInformacion();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void GuardarInformacion() {
        progressDialog.setMessage("Guardando su informacion");
        progressDialog.dismiss();
        String uid=firebaseAuth.getUid();
        HashMap<String, String> Datos=new HashMap<>();
        Datos.put("uid",uid);
        Datos.put("Correo",correo);
        Datos.put("nombre", nombre);
        Datos.put("contraseña",contra);

        Datos.put("apellidos","");
        Datos.put("edad","");
        Datos.put("telefono","");
        Datos.put("domicilio","");
        Datos.put("universidad","");
        Datos.put("profesion","");
        Datos.put("imagen_perfil","");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        databaseReference.child(uid)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, "Cuenta creada con exito", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registro.this, MenuPrinciapl.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(Registro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}