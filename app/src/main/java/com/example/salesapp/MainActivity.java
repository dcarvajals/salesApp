package com.example.salesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText et_numero_telefono = null;
    EditText et_codigo_pais = null;
    EditText et_numero_cedula = null;
    EditText et_direccion_referencia = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void comprar (View view) {
        Compra compra = new Compra();

        et_numero_telefono = (EditText)findViewById(R.id.et_numero_telefono);
        String numero_telefono = et_numero_telefono.getText().toString();
        compra.setNumero_telefono(numero_telefono);

        et_codigo_pais = (EditText)findViewById(R.id.et_codigo_pais);
        String codigo_pais = et_codigo_pais.getText().toString();
        compra.setCodigo_pais(codigo_pais);

        et_numero_cedula = (EditText)findViewById(R.id.et_numero_cedula);
        String numero_cedula = et_numero_cedula.getText().toString();
        compra.setCedula(numero_cedula);

        et_direccion_referencia = (EditText)findViewById(R.id.et_direccion_referencia);
        String direccion_referencia = et_numero_cedula.getText().toString();
        compra.setDireccion_referencia(direccion_referencia);

        compra.setRespuesta_url("http://paystoreCZ.com/confirm.php");
        compra.setMonto_pagar("100");
        compra.setImporte_con_impuesto("90");
        compra.setImporte_sin_impuesto("0");
        compra.setImpuesto("10");
        compra.setId_transaccion("abcd123");

        wsComprar(compra);

    }

    private void wsComprar (Compra compra) {
        String data = compra.getJsonCompra(compra);
        RequestQueue queue;
        queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "https://pay.payphonetodoesposible.com/api/Sale",
                new com.android.volley.Response.Listener<String>()  {
                    @Override
                    public void onResponse(String response) {
                        Log.d("","Ws ejecutado con exito");
                        Log.d("",response);
                        JsonObject jso = Methods.stringToJSON(response);
                        Log.d("","size del json" + jso.size());
                        if(jso.size() > 0){
                            Log.d("message", jso.toString());
                            Toast.makeText(MainActivity.this, "Compra realizada con éxito.", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type", "application/json");
                header.put("Accept", "application/json");
                header.put("Authorization",
                        "Bearer lNTlMsJBbnr8ngsJ7qK_QVfDOOrE-sjRbK4x9m4hp6k2P64VgoQhINEZ-x3FJFzSJI8RGyvBnqNiXarDuV2v5ovhH6UoqQk13DXFoOaYiLMmq-E1wV0hhr3pkJFmik69w5gmmr-2wQ64_NNK4tfbelqpiudz6H3B6Q-K6EYpPNb0Mm9w7TgB1aBHajg_8WRQi46CgeTtS6odewgq0N_NXVbUQBvaD8YPsDzeS-GtTj5_wJbppPaSi9k3xCqUy6VYhHRZei-AC0tmCSqm3qvqYLNVhnn9tQbkuKKUxQWQuWXm-R-nXo-7bouY_0vB0gT8yktYWZConODcBj5z-8GT9Rf4beA");
                return header;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public byte[] getBody() throws AuthFailureError {
                //los datos que se envian deben ir bajo este formato
                try {
                    return data == null ? "{}".getBytes("utf-8") : data.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    Log.d("","Error al momento de codificar la solicitud");
                    return null;
                }
            }
        };
        queue.add(request);
    }

}