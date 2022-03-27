package com.example.textcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TextCalculator extends AppCompatActivity {
    int first;
    int second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_calculator);

        //Извлечение Intent
        Intent intent = getIntent();
        first = intent.getIntExtra("first", first);
        second = intent.getIntExtra("second", second);
        TextView mainView = findViewById(R.id.textView3);
        //Вывод уравнения на экран
        if (second<0){
            mainView.setText(first + " + " +"("+ second + ")" + " = " + (first+second));
        }
        else{
            mainView.setText(first + " + " + second + " = " + (first+second));
        }
    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        TextView mainView = findViewById(R.id.textView3);
        outState.putString("mainText", String.valueOf(mainView.getText()));
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("mainText")) {
            int[] nums = savedInstanceState.getIntArray("nums");

        }
    }
}