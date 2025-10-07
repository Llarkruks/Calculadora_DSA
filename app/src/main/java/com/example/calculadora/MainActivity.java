package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtDisplay;
    Button button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9;
    Button button15, button16, button17, button18, button19; // + - x / =
    Button button20, button21, button22; // tan cos sin
    Button buttonClear; // C

    double num1 = 0, num2 = 0;
    String operation = "";
    boolean newOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Vincular elementos del layout ---
        txtDisplay = findViewById(R.id.txtDisplay);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        button17 = findViewById(R.id.button17);
        button18 = findViewById(R.id.button18);
        button19 = findViewById(R.id.button19);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        buttonClear = findViewById(R.id.buttonClear);

        // --- Listener para números ---
        View.OnClickListener numberListener = v -> {
            Button b = (Button) v;
            String value = b.getText().toString();
            if (newOp) {
                txtDisplay.setText(value);
                newOp = false;
            } else {
                txtDisplay.append(value);
            }
        };

        button0.setOnClickListener(numberListener);
        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);

        // --- Operaciones básicas ---
        button15.setOnClickListener(v -> setOperation("+"));
        button18.setOnClickListener(v -> setOperation("-"));
        button17.setOnClickListener(v -> setOperation("*"));
        button16.setOnClickListener(v -> setOperation("/"));

        // --- Botón igual (=) ---
        button19.setOnClickListener(v -> {
            num2 = Double.parseDouble(txtDisplay.getText().toString());
            double result = 0;

            switch (operation) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        txtDisplay.setText("Error");
                        return;
                    } else result = num1 / num2;
                    break;
            }

            txtDisplay.setText(String.valueOf(result));
            newOp = true;
        });

        // --- Botón Clear (C) ---
        buttonClear.setOnClickListener(v -> {
            txtDisplay.setText("0");
            num1 = num2 = 0;
            operation = "";
            newOp = true;
        });

        // --- Funciones trigonométricas ---
        button22.setOnClickListener(v -> applyTrig("sin"));
        button21.setOnClickListener(v -> applyTrig("cos"));
        button20.setOnClickListener(v -> applyTrig("tan"));
    }

    // --- Método para guardar la operación seleccionada ---
    private void setOperation(String op) {
        num1 = Double.parseDouble(txtDisplay.getText().toString());
        operation = op;
        newOp = true;
    }

    // --- Método para aplicar funciones trigonométricas ---
    private void applyTrig(String fn) {
        double val = Double.parseDouble(txtDisplay.getText().toString());
        double rad = Math.toRadians(val); // convierte a radianes
        double res = 0;

        switch (fn) {
            case "sin":
                res = Math.sin(rad);
                break;
            case "cos":
                res = Math.cos(rad);
                break;
            case "tan":
                res = Math.tan(rad);
                break;
        }

        txtDisplay.setText(String.valueOf(res));
        newOp = true;
    }
}
