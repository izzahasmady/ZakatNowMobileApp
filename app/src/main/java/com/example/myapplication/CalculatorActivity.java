package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar mytoolbar;
    EditText editWeight;
    EditText editValue;
    TextView tvOutput;
    TextView outputWeight;
    TextView outputPayable;
    TextView outputTotal;
    Button btnCalculate;
    Button btnReset;
    RadioButton rbKeep;
    RadioButton rbWear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        editWeight = findViewById(R.id.editTextNumberDecimal);
        editValue = findViewById(R.id.editTextNumberDecimal2);
        outputWeight = findViewById(R.id.outputWeight);
        outputPayable = findViewById(R.id.outputPayable);
        outputTotal = findViewById(R.id.outputTotal);
        tvOutput = findViewById(R.id.tvOutput);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        mytoolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        showInstructionDialog();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu mnu) {
        getMenuInflater().inflate(R.menu.mnu, mnu);
        return true;
    }
    private void showInstructionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Instructions to use Zakat Calculator");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        builder.setView(view);

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
        } else if (item.getItemId() == R.id.btnHelp) {
            showInstructionDialog(); // Call the method to show the instruction dialog
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculate) {
            calculateZakat();
        } else if (v.getId() == R.id.btnReset) {
            resetInputs();
        }
    }


    private void calculateZakat() {
        String weightText = editWeight.getText().toString().trim();
        String currentGoldValueText = editValue.getText().toString().trim();

        if (weightText.isEmpty() || currentGoldValueText.isEmpty()) {
            tvOutput.setText("Please enter values for Weight and Current Gold Value");
            return;
        }

        double weight = Double.parseDouble(weightText);
        double currentGoldValue = Double.parseDouble(currentGoldValueText);

        String type = getSelectedType();
        if (type == null) {
            tvOutput.setText("Please select 'Keep' or 'Wear'.");
            return;
        }

        double X;
        double goldWeightMinusX;
        double zakatPayable;

        if (rbKeep.isChecked()) {
            X = 85;
            goldWeightMinusX = weight - X;
            zakatPayable = (goldWeightMinusX > 0) ? goldWeightMinusX * currentGoldValue : 0;
        } else if (rbWear.isChecked()) {
            X = 200;
            goldWeightMinusX = weight - X;
            zakatPayable = (goldWeightMinusX > 0) ? goldWeightMinusX * currentGoldValue : 0;
        } else {
            tvOutput.setText("Invalid Type. Please enter 'keep' or 'wear'.");
            return;
        }

        double totalZakat = (goldWeightMinusX > 0) ? zakatPayable * 0.025 : 0;


        outputWeight.setText("Gold Weight (gram):  " + goldWeightMinusX + "\n");
        outputPayable.setText("Zakat Payable (RM): " + zakatPayable + "\n");
        outputTotal.setText( "Total Zakat (RM): " + totalZakat);
    }

    private void resetInputs() {
        editWeight.setText("");
        editValue.setText("");
        outputWeight.setText("");
        outputPayable.setText("");
        outputTotal.setText("");
        tvOutput.setText("");
        rbKeep.setChecked(false);
        rbWear.setChecked(false);
    }

    private String getSelectedType() {
        if (rbKeep.isChecked()) {
            return "keep";
        } else if (rbWear.isChecked()) {
            return "wear";
        } else {
            return null;
        }
    }
}
