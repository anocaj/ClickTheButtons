package com.sap.clickthebuttons;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    public Button button;
    public ConstraintLayout playArea;
    public DisplayMetrics displayMetrics;
    public Random randomizer;
    public int clickCountdown = 10;
    public long startTime;
    public float elapsedSeconds;
    public static final String EXTRA_MESSAGE = "com.sap.clickthebuttons.ELAPSED_SECONDS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        button = (Button) findViewById(R.id.button);
        //playArea = (ConstraintLayout) findViewById(R.id.playArea);
        displayMetrics = new DisplayMetrics();
        button.setText(Integer.toString(clickCountdown));
        button.setVisibility(View.VISIBLE);

        startTime = System.currentTimeMillis();

    }


    public void setButtonPositionRamdomly(){
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels - button.getWidth();
        int height = displayMetrics.heightPixels - 200 - button.getHeight();

        randomizer = new Random();

        //button.setX(randomizer.nextInt(width) + 44 );
        button.setX(randomizer.nextInt(width));
        button.setY(randomizer.nextInt(height));
        button.setText(Integer.toString(clickCountdown));

    }

    public void setToNextPosition(View view){

        clickCountdown = clickCountdown - 1;


        if (clickCountdown < 1) {
            elapsedSeconds = (float) (System.currentTimeMillis() - startTime) / 1000;

            finishGame();
            return;
        }
        setButtonPositionRamdomly();

    }

    public void finishGame(){
        Intent intent = new Intent(this, TitleActivity.class);


        intent.putExtra(EXTRA_MESSAGE, (Float.toString(elapsedSeconds)));
        startActivity(intent);
    }
}
