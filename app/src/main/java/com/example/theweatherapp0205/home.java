package com.example.theweatherapp0205;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class home extends AppCompatActivity {

    EditText etCity;
    TextView tvResults;


    //Used to store the URL
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "e02f6ca299a03760e55f478d07ae6757";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etCity = findViewById(R.id.etCity);
        tvResults = findViewById(R.id.tvResults);
    }

    public void moodBtn(View view) {

        Intent intent = new Intent(getApplicationContext(), moodui.class);
        startActivity(intent);
        finish();

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }

    public void searchBtn(View v) {


        String city = etCity.getText().toString().trim();

        String tempUrl = url + "?q=" + city + "&appid=" + appid;
        if (city.isEmpty()) {

            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            tvResults.setText("Opps, your text field is empty");

        }
        // Send API request using Volley library

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //Used to view the Json Response
                Log.d("response", s);

                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(s);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("description");

                    //The JsonObjectMain is created Here
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");

                    //The JsonObjectWind is created Here
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");

                    //The JsonObjectClouds is created Here
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");

                    //The JsonObjectSys is created Here
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");

                    String cityName = jsonResponse.getString("name");

                    // Display weather information
                    tvResults.setTextColor(Color.parseColor("#0C1823"));
                    tvResults.setTypeface(null, Typeface.BOLD); // Make text bold
                    tvResults.setTextSize(19);


                    output += " Current Weather in " + cityName
                            + "\n Temp: " + df.format(temp) + "C"
                            + "\n Feels Like: " + df.format(feelsLike) + "C"
                            + "\n Humidity: " + humidity + "%"
                            + "\n Description: " + description
                            + "\n Wind Speed: " + wind + "m/s (meters per second)"
                            + "\n Cloudiness: " + clouds + "%"
                            + "\n Pressure: " + pressure + "hPa";

                    tvResults.setText(output);


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

        }, new Response.ErrorListener() {
            private VolleyError volleyError;

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                this.volleyError = volleyError;
                Toast.makeText(getApplicationContext(), volleyError.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}
