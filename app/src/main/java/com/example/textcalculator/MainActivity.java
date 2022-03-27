package com.example.textcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int first = 0;
    public int second = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText firstTermInput = (EditText) findViewById(R.id.firstTermInput);
        EditText secondTermInput = (EditText) findViewById(R.id.secondTermInput);
        //Делает так чтобы при нажатии на EditText можно сразу было вводить значение
        firstTermInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    firstTermInput.setText("");
                }
            }
        });
        secondTermInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    secondTermInput.setText("");
                }
            }
        });
    }

    public void add(View view){
        TextView firstTerm = findViewById(R.id.firstTermInput);
        TextView secondTerm = findViewById(R.id.secondTermInput);
        //Поиск ошибок в вводе
        try {
            first = Integer.parseInt(String.valueOf(firstTerm.getText()));
            second = Integer.parseInt(String.valueOf(secondTerm.getText()));
            //Создание Intent и запуск воторой Activity
            Intent intent = new Intent(this, TextCalculator.class);
            intent.putExtra("first", first);
            intent.putExtra("second",second);
            startActivity(intent);
        }
        catch (Exception ex){
            //Создание и настройка диалгового окна
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Ошибка");
            builder.setMessage("Неверные входные данные, введите еще раз");
            builder.setPositiveButton("Ок", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id){
                    dialog.cancel();
                }
            });
            //Показ диалогового окна
            builder.show();
        }
    }


    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putIntArray("nums", new int[] {first, second});
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey("nums")) {
            int[] nums = savedInstanceState.getIntArray("nums");
            first = nums[0];
            second = nums[1];
            if (!(first == 0 && second == 0)){
                resetUI();
            }

        }
    }
    protected void resetUI(){
        TextView leftScore = findViewById(R.id.firstTermInput);
        leftScore.setText(String.valueOf(first));
        TextView rightScore = findViewById(R.id.secondTermInput);
        rightScore.setText(String.valueOf(second));
    }
}