package com.example.nippov2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SalvarEspecialidadeRegiaoTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "SalvarEspecialidadeRegiaoTask";

    // Interface para lidar com eventos de sucesso e erro ao salvar
    private SalvarEspecialidadeRegiaoListener mListener;

    // Interface para o ouvinte
    public interface SalvarEspecialidadeRegiaoListener {
        void onSalvarSuccess(String message);
        void onSalvarError(String message);
    }

    // Construtor para definir o ouvinte
    public SalvarEspecialidadeRegiaoTask(SalvarEspecialidadeRegiaoListener listener) {
        mListener = listener;
    }

    // Método que executa a tarefa em segundo plano
    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String especialidade = params[1];
        String regiao = params[2];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            // Configuração da conexão HTTP
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            // Construção dos dados JSON a serem enviados
            JSONObject requestData = new JSONObject();
            requestData.put("especialidade", especialidade);
            requestData.put("regiao", regiao);

            // Envio dos dados JSON para o servidor
            OutputStream os = urlConnection.getOutputStream();
            os.write(requestData.toString().getBytes());
            os.flush();

            // Recebimento da resposta do servidor
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder buffer = new StringBuilder();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            return buffer.toString();
        } catch (IOException | JSONException e) {
            Log.e(TAG, "Error ", e);
            return null;
        } finally {
            // Fechamento das conexões e fluxos
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
    }

    // Método chamado após a conclusão da tarefa em segundo plano
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            // Tratamento da resposta do servidor
            try {
                JSONObject jsonResponse = new JSONObject(result);
                String message = jsonResponse.getString("message");
                mListener.onSalvarSuccess(message);
            } catch (JSONException e) {
                // Se houver erro na interpretação da resposta, informa sucesso genérico
                mListener.onSalvarSuccess("Salvo no Banco de dados!");
            }
        } else {
            // Se não houver resposta, informa erro de comunicação
            mListener.onSalvarError("Erro de comunicação com o servidor");
        }
    }
}
