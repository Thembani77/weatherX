package com.example.theweatherapp0205;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import io.github.muddz.styleabletoast.StyleableToast;

public class moodui extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_moodui);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void backButton(View v)
    {
        Intent intent = new Intent(getApplicationContext(), home.class);
        startActivity(intent);
        finish();

    }

    public void verygood(View v)
    {

        StyleableToast.makeText(this, "Suggestions coming soon",Toast.LENGTH_SHORT, R.style.Comingsoon).show();
    }

    public void okish(View v)
    {

        StyleableToast.makeText(this, "Suggestions coming soon", Toast.LENGTH_SHORT, R.style.Comingsoon).show();

    }

    public void verybad(View v)
    {

        StyleableToast.makeText(this, "Suggestions coming soon",Toast.LENGTH_SHORT, R.style.Comingsoon).show();

    }

    public void angry(View v)
    {

        StyleableToast.makeText(this, "Suggestions coming soon",Toast.LENGTH_SHORT, R.style.Comingsoon).show();
    }
    public void sad(View v){

        StyleableToast.makeText(this, "Suggestions coming soon",Toast.LENGTH_SHORT, R.style.Comingsoon).show();

    }

    public void veryhappy(View v)
    {

        StyleableToast.makeText(this, "Suggestions coming soon",Toast.LENGTH_SHORT, R.style.Comingsoon).show();
    }




}