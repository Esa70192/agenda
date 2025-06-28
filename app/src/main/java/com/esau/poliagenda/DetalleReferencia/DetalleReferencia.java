package com.esau.poliagenda.DetalleReferencia;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.esau.poliagenda.R;

public class DetalleReferencia extends AppCompatActivity {
    private TextView nombreTextView,autorTextView, tituloTextView, editorialTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_referencia);
        ActionBar  actionBar= getSupportActionBar();
        actionBar.setTitle("Bibliografía");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String autor = getIntent().getStringExtra("autor");
        String titulo = getIntent().getStringExtra("titulo");
        String editorial = getIntent().getStringExtra("editorial");
        String nombre = getIntent().getStringExtra("nombre");

        autorTextView = findViewById(R.id.nombreAutor);
        tituloTextView = findViewById(R.id.titulo);
        editorialTextView = findViewById(R.id.nombreEditorial);
        nombreTextView = findViewById(R.id.detailName);

        autorTextView.setText(autor);
        tituloTextView.setText(titulo);
        editorialTextView.setText(editorial);
        nombreTextView.setText(nombre);

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}