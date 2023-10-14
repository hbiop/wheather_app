package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button get;
TextView text;
String API_KEY = "ccdf90f3051847bc820143629231410";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }
    public void Init()
    {
        text = (TextView) findViewById(R.id.textview);
        get = (Button) findViewById(R.id.button);
        get.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                GetResult("London");
                break;
        }
    }
    public void GetResult(String name)
    {
        String URL = "https://api.weatherapi.com/v1/current.json" + "?key=" + API_KEY + "&q=" + name + "&aqi=no";
        StringRequest stringRequest =  new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONObject temp = obj.getJSONObject("current");
                    String temperature = temp.getString("temp_c");
                    text.setText(temperature);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}