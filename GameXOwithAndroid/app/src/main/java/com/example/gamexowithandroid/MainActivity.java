package com.example.gamexowithandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EditText playerName = findViewById(R.id.playerName);
        Button startEasyLevelButton = findViewById(R.id.startEasyLevelButton);

        startEasyLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPlayerName = playerName.getText().toString();
                if (getPlayerName.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter plaeyr name", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, PlayingFieldEasy.class);
                    intent.putExtra("playerName", getPlayerName);
                    startActivity(intent);
                }
            }
        });


    }
}