package com.esau.poliagenda.Detalle;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esau.poliagenda.R;

public class Detalle_Nota extends AppCompatActivity {

    TextView Id_nota_Detalle, Uid_usuario_Detalle, Correo_usuario_Detalle, Titulo_Detalle, Descripcion_Detalle, Fecha_Registro_Detalle, Fecha_Nota_Detalle, Estado_Detalle;
    String id_nota_R, uid_usuario_R, correo_usuario_R, fecha_registro_R, titulo_R, descripcion_R, fecha_R, estado_R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Detalle de tarea");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        InicializarVistar();
        RecuperarDatos();
        SetearDatosRecuperados();
    }

    private void InicializarVistar(){
        Id_nota_Detalle=findViewById(R.id.Id_nota_Detalle);
        Uid_usuario_Detalle=findViewById(R.id.Uid_usuario_Detalle);
        Correo_usuario_Detalle=findViewById(R.id.Correo_usuario_Detalle);
        Titulo_Detalle=findViewById(R.id.Titulo_Detalle);
        Descripcion_Detalle=findViewById(R.id.Descripcion_Detalle);
        Fecha_Registro_Detalle=findViewById(R.id.Fecha_Registro_Detalle);
        Fecha_Nota_Detalle=findViewById(R.id.Fecha_Nota_Detalle);
        Estado_Detalle=findViewById(R.id.Estado_Detalle);
    }

    private void RecuperarDatos(){
        Bundle intent=getIntent().getExtras();
        id_nota_R=intent.getString("id_nota");
        uid_usuario_R=intent.getString("uid_usuario");
        fecha_registro_R= intent.getString("fecha_registro");
        correo_usuario_R=intent.getString("correo_usuario");
        titulo_R=intent.getString("titulo");
        descripcion_R=intent.getString("descripcion");
        fecha_R=intent.getString("fecha_nota");
        estado_R=intent.getString("estado");
    }

    private void SetearDatosRecuperados(){
        Id_nota_Detalle.setText(id_nota_R);
        Uid_usuario_Detalle.setText(uid_usuario_R);
        Correo_usuario_Detalle.setText(correo_usuario_R);
        Fecha_Registro_Detalle.setText(fecha_registro_R);
        Titulo_Detalle.setText(titulo_R);
        Descripcion_Detalle.setText(descripcion_R);
        Fecha_Nota_Detalle.setText(fecha_R);
        Estado_Detalle.setText(estado_R);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}