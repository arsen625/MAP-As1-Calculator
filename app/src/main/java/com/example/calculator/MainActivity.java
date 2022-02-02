package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.view.View;
import android.util.Log;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.view.View;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    String tag = "Assignment 1";
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button num0;

    Button plusBut;
    Button timesBut;
    Button dividBut;
    Button minusBut;
    Button advanceBut;
    Button cBut;
    Button equalBut;
    Button POWBut;
    Button ModBut;
    Button MinBut;
    Button MaxBut;

    TextView resultText;
    TextView advanceText;

    AlertDialog.Builder builder;

    private Calculator myCalculator;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"in OnCreate");
        setContentView(R.layout.activity_main);

        resultText = (TextView) findViewById(R.id.textOutPut);

        timesBut = (Button) findViewById(R.id.btnMultiply);
        plusBut = (Button) findViewById(R.id.btnPlus);
        minusBut = (Button) findViewById(R.id.btnMinus);
        dividBut = (Button)findViewById(R.id.btnDivide);
        resultText = (TextView) findViewById(R.id.textOutPut);
        advanceText = (TextView) findViewById(R.id.advanceText);

        num1 = (Button) findViewById(R.id.btn1);
        num2 = (Button) findViewById(R.id.btn2);
        num3 = (Button) findViewById(R.id.btn3);
        num4 = (Button) findViewById(R.id.btn4);
        num5 = (Button) findViewById(R.id.btn5);
        num6= (Button) findViewById(R.id.btn6);
        num7 = (Button) findViewById(R.id.btn7);
        num8 = (Button) findViewById(R.id.btn8);
        num9 = (Button) findViewById(R.id.btn9);
        num0 = (Button) findViewById(R.id.btn0);

        POWBut = (Button) findViewById(R.id.btnPower);
        ModBut =(Button) findViewById(R.id.btnModulo);
        MinBut =(Button) findViewById(R.id.btnMin);
        MaxBut =(Button) findViewById(R.id.btnMax);

        advanceBut = (Button) findViewById(R.id.advance);
        cBut = (Button) findViewById(R.id.btnClear);
        equalBut = (Button) findViewById(R.id.btnEqual);

        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        timesBut.setOnClickListener(this);
        plusBut.setOnClickListener(this);
        minusBut.setOnClickListener(this);
        dividBut.setOnClickListener(this);

        POWBut.setOnClickListener(this);
        ModBut.setOnClickListener(this);
        MinBut.setOnClickListener(this);
        MaxBut.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);

        advanceText.setEnabled(false);

        LinearLayout scientificLayer = (LinearLayout) findViewById(R.id.scientificButtons);

        scientificLayer.setVisibility(View.GONE);

        advanceBut.setOnClickListener(view -> {

            if (advanceText.isEnabled()) {
                advanceText.setEnabled(false);
                advanceText.setText("");
                advanceBut.setText(R.string.advance_scientific);
                scientificLayer.setVisibility(View.GONE);

            }else {
                advanceText.setEnabled(true);
                advanceBut.setText(R.string.standard);
                scientificLayer.setVisibility(View.VISIBLE);

            }
        });


        cBut.setOnClickListener(view -> {

            myCalculator.list.clear();
            resultText.setText("");

        });


        equalBut.setOnClickListener(view -> {
            if(!myCalculator.validateCalculator()){

                builder.create();
                builder.setTitle("Error!");
                builder.setMessage("Operation is too short!" );
                builder.show();
                return;

            }
            //get the result
            String result = String.valueOf(myCalculator.calculate());

            //update Text with the result
            resultText.setText( resultText.getText().toString() + " = " +  result );
            //clear the list Arraylist
            myCalculator.list.clear();


        });
    }


    @Override
    public void onClick(View view) {
        prepareForCalc(view);
    }

    void prepareForCalc(View view){
        String operation = "";

        if( !myCalculator.validateUserInput(((Button)view).getText().toString())){
            builder.create();
            builder.setTitle("Error!");
            builder.setMessage("Only one Digit or one Operand at a time." );
            builder.show();
            return;
        }

        myCalculator.push(((Button)view).getText().toString());
        for (String s : myCalculator.list) {

            operation += s;
            resultText.setText(operation);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(tag,"in onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(tag,"in onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myCalculator = new Calculator();
        Log.d(tag,"in onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(tag,"in onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag,"in onDestroy");
    }



}