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

public class AlterarDadosTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "AlterarDadosTask";
    private AlterarDadosListener mListener;

    public interface AlterarDadosListener {
        void onAlterarDadosSuccess(String message);
        void onAlterarDadosError(String message);
    }

    public AlterarDadosTask(AlterarDadosListener listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String jsonData = params[1];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            URL requestUrl = new URL(url);
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = urlConnection.getOutputStream();
            os.write(jsonData.getBytes());
            os.flush();

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
        } catch (IOException e) {
            Log.e(TAG, "Error ", e);
            return null;
        } finally {
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

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            try {
                JSONObject jsonResponse = new JSONObject(result);
                String message = jsonResponse.getString("message");
                mListener.onAlterarDadosSuccess(message);
            } catch (JSONException e) {
                mListener.onAlterarDadosSuccess("Dados do usuario alterado!");
            }
        } else {
            mListener.onAlterarDadosError("Erro de comunicação com o servidor");
        }
    }
}

