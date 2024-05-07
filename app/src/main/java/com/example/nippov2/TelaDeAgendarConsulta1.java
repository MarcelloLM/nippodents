package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class TelaDeAgendarConsulta1 extends AppCompatActivity {

    private Spinner spinnerEspecialidade;
    private Spinner spinnerRegiao;
    private Button btnVoltarParaAgendamento;
    private Button btnProximoPasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_agendar_consulta1);

        // Referencie os Spinners do seu layout XML
        spinnerEspecialidade = findViewById(R.id.spinnerEspecialidade);
        spinnerRegiao = findViewById(R.id.spinnerRegiao);

        // Popule os Spinners com as opções desejadas
        String[] especialidades = {"Odontologia Geral", "Ortodontia", "Implantodontia"};
        String[] regioes = {"São Paulo", "São Paulo - Zona Sul", "São Paulo - Zona Norte"};

        ArrayAdapter<String> adapterEspecialidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, especialidades);
        ArrayAdapter<String> adapterRegiao = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regioes);

        adapterEspecialidade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterRegiao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerEspecialidade.setAdapter(adapterEspecialidade);
        spinnerRegiao.setAdapter(adapterRegiao);

        // Configurar o clique do botão "Próximo Passo"
        btnProximoPasso = findViewById(R.id.btnProximoPasso);
        btnProximoPasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TelaDeAgendarConsulta1", "Botão Próximo Passo clicado");

                // Obter a especialidade e a região selecionadas pelo usuário
                String especialidadeSelecionada = spinnerEspecialidade.getSelectedItem().toString();
                String regiaoSelecionada = spinnerRegiao.getSelectedItem().toString();

                // Inicialize e execute a tarefa para salvar no banco de dados
                SalvarEspecialidadeRegiaoTask salvarTask = new SalvarEspecialidadeRegiaoTask(new SalvarEspecialidadeRegiaoTask.SalvarEspecialidadeRegiaoListener() {
                    @Override
                    public void onSalvarSuccess(String message) {
                        // Se a operação de salvar for bem-sucedida, inicie a próxima tela
                        Intent intent = new Intent(TelaDeAgendarConsulta1.this, TelaDeAgendarConsulta2.class);
                        intent.putExtra("especialidade", especialidadeSelecionada);
                        intent.putExtra("regiao", regiaoSelecionada);
                        startActivity(intent);
                    }

                    @Override
                    public void onSalvarError(String message) {
                        // Se houver um erro ao salvar, mostre uma mensagem de erro ao usuário
                        // Por exemplo, você pode exibir um Toast informando sobre o erro
                    }
                });
                salvarTask.execute(especialidadeSelecionada, regiaoSelecionada);
            }
        });

        btnVoltarParaAgendamento = findViewById(R.id.btnVoltarParaAgendamento);
        btnVoltarParaAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
