package com.rmoralessolo2016.weatherrams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String WEATHER_API_KEY = "/*YOUR API KEY*/";
    public static final String URL = "https://api.openweathermap.org/data/2.5/weather?appid=";
    public static final String EXTRA_MESSAGE = "com./*example*/.weatherrams.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of needed items
        EditText userIN = (EditText) findViewById(R.id.user_input);
        ImageButton go_Button = (ImageButton) findViewById(R.id.goButton);
        ImageButton location_Button = (ImageButton) findViewById(R.id.LocationButton);
        // city or zip value

        //adding listener to go button
        go_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String City_Zip = userIN.getText().toString();
                //check if user input is empty
                if (City_Zip.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Can not enter and empty value \n - Try again.", Toast.LENGTH_SHORT).show();
                } else { //if not empty then
                    //check if city or zip
                    if (City_Zip.matches("\\d+(?:\\.\\d+)?")){
                        //if number then check length --> it must be 5 numbers
                        Log.e("TAG", "its a Zip-code");
                        if (City_Zip.length() == 5){
                            //correct zip format
                            onZipGO(City_Zip);
                        } else{
                            //incorrect length show message
                            Toast.makeText(getApplicationContext(), "Incorrect Zip format \n - Try again.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("TAG", "its a City ");
                        onCityGO(City_Zip);
                    }

                }
            }
        });
    } // end of onCreate

    public void onZipGO(String value){
        //creating url for ZIP
        String url = URL + WEATHER_API_KEY + "&zip=" + value;
        Log.e("TAG","the url is: " + url);
        sendURL(url);
    } // end of onZipGO

    public void onCityGO(String value){
        //creating the url for City
        String url = URL + WEATHER_API_KEY + "&q=" + value;
        Log.e("TAG", "the url is: " + url);
        sendURL(url);
    }// end of onCityGO

    public void sendURL(String url){
        Intent intent = new Intent(getApplicationContext(),weather_information.class);
        intent.putExtra(EXTRA_MESSAGE, url);
        startActivity(intent);
    } //end of sendURL

} //end of MainActivity