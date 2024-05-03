package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class TelaDeAgendarConsulta1 extends AppCompatActivity {

    private Button btnVoltarParaAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_agendar_consulta1);

        btnVoltarParaAgendamento = findViewById(R.id.btnVoltarParaAgendamento);
        btnVoltarParaAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Referencie o Spinner do seu layout XML
        Spinner spinner = findViewById(R.id.spinner);

        // Crie um ArrayAdapter com as opções que você deseja exibir no Spinner
        String[] opcoes = {"Opção 1", "Opção 2", "Opção 3"}; // Substitua pelas suas opções
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opcoes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Associe o ArrayAdapter ao Spinner
        spinner.setAdapter(adapter);
    }
}

