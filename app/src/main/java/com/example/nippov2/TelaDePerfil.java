package com.example.nippov2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;
import android.widget.EditText;

public class TelaDePerfil extends AppCompatActivity implements AlterarDadosTask.AlterarDadosListener{

    private EditText editTxtCPFPerfil;
    private EditText editTxtNomePerfil;
    private EditText editTxtEmailPerfil;
    private EditText editTxtCarteirinhaPerfil2;
    private Button btnAlterarDados;

    private Button btnVoltarAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_perfil);

        editTxtCPFPerfil = findViewById(R.id.editTxtCPFPerfil);
        editTxtNomePerfil = findViewById(R.id.editTxtNomePerfil);
        editTxtEmailPerfil = findViewById(R.id.editTxtEmailPerfil);
        editTxtCarteirinhaPerfil2 = findViewById(R.id.editTxtCarteirinhaPerfil2);
        btnAlterarDados = findViewById(R.id.btnAlterarDados);

        btnAlterarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarDadosParaServidor();
            }
        });

        btnVoltarAgendamento = findViewById(R.id.btnVoltarAgendamento);
        btnVoltarAgendamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void enviarDadosParaServidor() {
        String cpf = editTxtCPFPerfil.getText().toString();
        String nome = editTxtNomePerfil.getText().toString();
        String email = editTxtEmailPerfil.getText().toString();
        String carteirinha = editTxtCarteirinhaPerfil2.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cpf", cpf);
            jsonObject.put("nome", nome);
            jsonObject.put("email", email);
            jsonObject.put("carteirinha", carteirinha);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AlterarDadosTask alterarDadosTask = new AlterarDadosTask((AlterarDadosTask.AlterarDadosListener) this);
        alterarDadosTask.execute("https://45fvnj-3000.csb.app/alterar", jsonObject.toString());
    }

    @Override
    public void onAlterarDadosSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlterarDadosError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
