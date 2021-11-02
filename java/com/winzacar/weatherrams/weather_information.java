package com.rmoralessolo2016.weatherrams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class weather_information extends AppCompatActivity {
    //declaring all the items that are going to be saved form the JSON reponse
    public String zip_OR_city, temp, feel_like, min_Temp, max_Temp, humidity, preasure, lat, lon, country, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_information);

        // get intent from other activity and get string
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //declare items
        TextView textView = (TextView) findViewById(R.id.recive_info);
        Log.e("TAG", url);



        //doing the request with volley
        RequestQueue queue = Volley.newRequestQueue(this);

        //request a string from the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                handleResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("that is not working");
            }
        });

        //add the request to the RequestQueue
        queue.add(stringRequest);

    }// end of onCreate

    public void handleResponse(String response){
        //check if response is empty
        if( response != null){
            Log.e("TAG", response);
            try{
                //start parsing JSON object
                JSONObject jsonObject =  new JSONObject(response);
            } catch (JSONException e) {
                //not reading correctly the json
               Log.e("TAG", "json parsing error: " + e.getMessage());
            }
        } else{
            Log.e("TAG", "response is empty ");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LogCat for possible errors!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
} //