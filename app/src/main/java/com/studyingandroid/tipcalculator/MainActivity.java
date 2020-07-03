package com.studyingandroid.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText editValue;
    private TextView textPercentage;
    private TextView textTipValue;
    private TextView textTotalValue;
    private SeekBar seekBarPercentage;
    private double percentage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValue = findViewById(R.id.text_value);
        textPercentage = findViewById(R.id.text_percentage);
        textTipValue = findViewById(R.id.text_tip_total);
        textTotalValue = findViewById(R.id.text_total_value);
        seekBarPercentage = findViewById(R.id.seekbar_percentage);

        seekBarPercentage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentage = progress;
                textPercentage.setText(Math.round(percentage) + " %");
                calculate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void calculate(){
        String inputValueString = editValue.getText().toString();
        if(Objects.isNull(inputValueString) || inputValueString.isEmpty()){
            Toast.makeText(getApplicationContext(), "Insert a value first", Toast.LENGTH_LONG).show();
        }
        else{
            double inputValueDouble =  Double.parseDouble(inputValueString);
            double percentageDouble = inputValueDouble * (percentage/100);
            double totalValue = inputValueDouble + percentageDouble;
            textTipValue.setText(""+percentageDouble);
            textTotalValue.setText(""+totalValue);
        }
    }
}