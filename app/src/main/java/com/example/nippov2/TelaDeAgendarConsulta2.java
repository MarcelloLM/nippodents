package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class TelaDeAgendarConsulta2 extends AppCompatActivity {

    private Button btnVoltarParaAgendamento1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_agendar_consulta2);

        btnVoltarParaAgendamento1 = findViewById(R.id.btnVoltarTelaAgendamento1);
        btnVoltarParaAgendamento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CalendarView calendarView = findViewById(R.id.calendarView);
        Button btnConfirmarAgendamento = findViewById(R.id.btnConfirmarAgendamento);

        // Defina um listener para capturar a data selecionada
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // Aqui você pode lidar com a data selecionada
                String dataSelecionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(TelaDeAgendarConsulta2.this, "Data selecionada: " + dataSelecionada, Toast.LENGTH_SHORT).show();
            }
        });

        // Defina um listener para o botão de confirmação de agendamento
        btnConfirmarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aqui você pode exibir um alerta confirmando as informações da consulta
                // Por exemplo:
                // - Mostre um AlertDialog com os detalhes da consulta (data, horário, especialidade, etc.)
                // - Peça ao usuário para confirmar o agendamento
                // - Execute a lógica de agendamento (por exemplo, salvar no banco de dados)
            }
        });
    }
}


