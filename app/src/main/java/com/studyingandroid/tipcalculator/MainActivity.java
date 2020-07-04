package com.studyingandroid.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button buttonReset;
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
        buttonReset = findViewById(R.id.button_reset);

        //buttonReset.setOnClickListener();

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

    public void resetValues(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(true); // false if you want to keep the dialog open even if user click on background
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("Are you sure you want to reset the values?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                seekBarPercentage.setProgress(0);
                editValue.setText("");
                textTipValue.setText("0.0");
                textTotalValue.setText("0.0");
                percentage = 0;
                textPercentage.setText("0%");

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.create();
        alertDialog.show();

    }
}