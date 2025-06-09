package com.esau.poliagenda;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esau.poliagenda.DetalleReferencia.DetalleReferencia;
import com.esau.poliagenda.ListarNotas.Listar_Notas;
import com.esau.poliagenda.Listar_referencias.Listar_referencias;
import com.esau.poliagenda.Perfil.Perfil_Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MenuPrinciapl extends AppCompatActivity {

    Button  ListarNotas, AcercaDe, CerrarSesion, Referencia;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView UidPrincipal, NombrePirncipal, CorreoPrincipal;
    Button EstadoCuentaPrincipal;
    ProgressBar ProgressBarDatos;
    ProgressDialog progressDialog;
    LinearLayoutCompat Linear_Nombres, Linear_Correo, Linear_Verificacion;

    DatabaseReference Usuarios;
    Dialog dialog_informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_princiapl);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("PoliAgenda");

        UidPrincipal=findViewById(R.id.UidPrincipal);
        NombrePirncipal=findViewById(R.id.NombrePrincipal);
        CorreoPrincipal=findViewById(R.id.CorreoPrincipal);
        EstadoCuentaPrincipal=findViewById(R.id.EstadoCuentaPrincipal);
        ProgressBarDatos=findViewById(R.id.ProgressBarDatos);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Espere por favor...");
        progressDialog.setCanceledOnTouchOutside(false);

        dialog_informacion=new Dialog(this);

        Linear_Nombres=findViewById(R.id.Linear_Nombres);
        Linear_Correo=findViewById(R.id.Linear_Correo);
        Linear_Verificacion=findViewById(R.id.Linear_Verificacion);

        Usuarios= FirebaseDatabase.getInstance().getReference("Usuarios");

        //AgregarNotas=findViewById(R.id.AgregarNotas);
        ListarNotas=findViewById(R.id.ListarNotas);
        Referencia=findViewById(R.id.Referencia);
        //Contactos=findViewById(R.id.Contactos);
        AcercaDe=findViewById(R.id.AcercaDe);
        CerrarSesion=findViewById(R.id.CerrarSesion);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();

        EstadoCuentaPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.isEmailVerified()){
                    Toast.makeText(MenuPrinciapl.this, "Cuenta ya verificada", Toast.LENGTH_SHORT).show();
                }else{
                    VerificarCuentaCorre();
                }
            }
        });


        ListarNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrinciapl.this, Listar_Notas.class));
                Toast.makeText(MenuPrinciapl.this, "Listar Notas", Toast.LENGTH_SHORT).show();
            }
        });

        Referencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuPrinciapl.this, Listar_referencias.class));
                Toast.makeText(MenuPrinciapl.this, "Referencias", Toast.LENGTH_SHORT).show();
            }
        });



        AcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MenuPrinciapl.this, "Acerca De", Toast.LENGTH_SHORT).show();
                Informacion();
            }
        });

        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalirAplicacion();
            }
        });
    }

    private void VerificarCuentaCorre() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Verificar cuenta")
                .setMessage("¿Estas segur@ de enviar instrucciones de verificacion a su correo electronico?"
                +user.getEmail())
                .setPositiveButton("Enviear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EnviarCorreoAVerificacion();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MenuPrinciapl.this, "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    private void EnviarCorreoAVerificacion() {
        progressDialog.setMessage("Enviando instrucciones de verificacion a su correo electronico"+user.getEmail());
        progressDialog.show();
        user.sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(MenuPrinciapl.this, "Instrucciones enviadas"+user.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuPrinciapl.this, "Fallo debido a: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void VerificacionEstadoDeCuenta(){
        String Verificado="Verificado";
        String No_Verificado="No verificado";
        if(user.isEmailVerified()){
            EstadoCuentaPrincipal.setText(Verificado);
            EstadoCuentaPrincipal.setBackgroundColor(Color.rgb(41,128,185));
        }else{
            EstadoCuentaPrincipal.setText(No_Verificado);
            EstadoCuentaPrincipal.setBackgroundColor(Color.rgb(234,76,60));
        }
    }

    private void Informacion() {
        Button EntendidoInfo;

        dialog_informacion.setContentView(R.layout.cuadro_dialogo_informacion);

        EntendidoInfo = dialog_informacion.findViewById(R.id.EntendidoInfo);

        EntendidoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_informacion.dismiss();
            }
        });
        dialog_informacion.show();
        dialog_informacion.setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onStart() {
        ComprobarInicioSesion();
        super.onStart();
    }

    private void ComprobarInicioSesion(){
        if(user!=null){
            CargaDeDatos();
        }else {
            startActivity(new Intent(MenuPrinciapl.this, MainActivity.class));
            finish();
        }
    }

    private void CargaDeDatos(){

        VerificacionEstadoDeCuenta();

        Usuarios.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Si el usuario existe
                if(snapshot.exists()){
                    //El progressbar se oculta
                    ProgressBarDatos.setVisibility(View.GONE);
                    //Los TextView se muestran
                    //UidPrincipal.setVisibility(View.VISIBLE);
                    //NombrePirncipal.setVisibility(View.VISIBLE);
                    //CorreoPrincipal.setVisibility(View.VISIBLE);
                    Linear_Nombres.setVisibility(View.VISIBLE);
                    Linear_Correo.setVisibility(View.VISIBLE);
                    Linear_Verificacion.setVisibility(View.VISIBLE);

                    //Obtener datos
                    String uid=""+snapshot.child("uid").getValue();
                    String nombre= ""+snapshot.child("nombre").getValue();
                    String correo= ""+snapshot.child("Correo").getValue();

                    //Setear los datos en los respectivos TextView
                    UidPrincipal.setText(uid);
                    NombrePirncipal.setText(nombre);
                    CorreoPrincipal.setText(correo);

                    //Habilitar los botones del menu

                    ListarNotas.setEnabled(true);
                    Referencia.setEnabled(true);
                    //Archivados.setEnabled(true);
                    //Contactos.setEnabled(true);
                    AcercaDe.setEnabled(true);
                    CerrarSesion.setEnabled(true);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Perfil_usuario){
            startActivity(new Intent(MenuPrinciapl.this, Perfil_Usuario.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(MenuPrinciapl.this, MainActivity.class));
        Toast.makeText(this, "Cerraste sesión exitosamente", Toast.LENGTH_SHORT).show();
    }
}