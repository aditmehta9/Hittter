package com.example.android.hittter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameoverActivity extends AppCompatActivity {
    private Button startgame;
    private TextView scores;
    private String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        score = getIntent().getExtras().get("score").toString();
        startgame = findViewById(R.id.button);
        scores = findViewById(R.id.textView2);
        startgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameoverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    scores.setText("SCORE = " + ""+score);
    }
}
