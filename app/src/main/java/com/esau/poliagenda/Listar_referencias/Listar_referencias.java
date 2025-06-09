package com.esau.poliagenda.Listar_referencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.esau.poliagenda.R;

public class Listar_referencias extends AppCompatActivity {
    RecyclerView recyclerViewReferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_referencias);
    }
}