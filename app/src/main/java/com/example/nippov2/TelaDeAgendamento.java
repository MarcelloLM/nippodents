package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class TelaDeAgendamento extends AppCompatActivity {

    private Button btnPerfilAgendamento;
    private Button btnAgendamento;
    private Button btnConsultaAgendadas;
    private Button btnAvaliarAplicativo;
    private Button btnSairAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_agendamento);

        btnPerfilAgendamento = findViewById(R.id.btnPerfilAgendamento);
        btnPerfilAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeAgendamento.this, TelaDePerfil.class);
                startActivity(intent);
            }
        });
        btnAgendamento = findViewById(R.id.btnAgendamento);
        btnAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeAgendamento.this, TelaDeAgendarConsulta1.class);
                startActivity(intent);
            }
        });
        btnConsultaAgendadas = findViewById(R.id.btnConsultaAgendamento);
        btnConsultaAgendadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeAgendamento.this, TelaDeConsultasAgendadas.class);
                startActivity(intent);
            }
        });
        btnAvaliarAplicativo = findViewById(R.id.btnAvaliarAplicativo);
        btnAvaliarAplicativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaDeAgendamento.this, TelaDeAvaliarAplicativo.class);
                startActivity(intent);
            }
        });
        btnSairAgendamento = findViewById(R.id.btnSairAgendamento);
        btnSairAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }
}