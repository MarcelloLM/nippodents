package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TelaDeAvaliarAplicativo extends AppCompatActivity {

    private Button btnVoltarTelaAgendamentoAvalicacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_avaliar_aplicativo);

        Button btnVoltarTelaAgendamentoAvaliacao = findViewById(R.id.btnVoltarTelaAgendamentoAvalicacao);
        btnVoltarTelaAgendamentoAvaliacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicie a atividade da tela de agendamento
                Intent intent = new Intent(TelaDeAvaliarAplicativo.this, TelaDeAgendamento.class);
                startActivity(intent);
                // Encerre esta atividade
                finish();
            }
        });
    }
}
