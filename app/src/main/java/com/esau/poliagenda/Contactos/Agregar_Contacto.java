package com.esau.poliagenda.Contactos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esau.poliagenda.R;

public class Agregar_Contacto extends AppCompatActivity {

    TextView Uid_Usuario_C;
    EditText Nombres_C, Apellidos_C, Correo_C, Telefono_C, Edad_C, Direccion_C;
    Button Btn_Guardar_Contacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_contacto);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        InicializarVariables();
        ObtenerUidUsuario();
    }

    private void InicializarVariables(){
        Uid_Usuario_C=findViewById(R.id.Uid_Usuario_C);
        Nombres_C=findViewById(R.id.Nombres_C);
        Apellidos_C=findViewById(R.id.Apellidos_C);
        Correo_C=findViewById(R.id.Correo_C);
        Telefono_C=findViewById(R.id.Telefono_C);
        Edad_C=findViewById(R.id.Edad_C);
        Direccion_C=findViewById(R.id.Direccion_C);
        Btn_Guardar_Contacto=findViewById(R.id.Btn_Guardar_Contacto);
    }

    private void ObtenerUidUsuario(){
        String UidRecuperado=getIntent().getStringExtra("Uid");
        Uid_Usuario_C.setText(UidRecuperado);
    }
}