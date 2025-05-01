package com.example.calculatorapp; 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import androidx.appcompat.app.AppCompatActivity; 
public class MainActivity extends AppCompatActivity { 
Button one, two, three, four, five, six, seven, eight, nine, zero, add, multiply, subtract, divide, 
point, clear, equal; 
EditText editTextText; 
boolean tAdd, tSub, tMul, tDiv; 
float mValue1, mValue2; 
@Override 
protected void onCreate(Bundle savedInstanceState) { 
super.onCreate(savedInstanceState); 
setContentView(R.layout.activity_main); 
one = findViewById(R.id.one); 
two = findViewById(R.id.two); 
three = findViewById(R.id.three); 
four = findViewById(R.id.four); 
five = findViewById(R.id.five); 
six = findViewById(R.id.six); 
seven = findViewById(R.id.seven); 
eight = findViewById(R.id.eight); 
nine = findViewById(R.id.nine); 
zero = findViewById(R.id.zero); 
add = findViewById(R.id.add); 
multiply = findViewById(R.id.multiply); 
subtract = findViewById(R.id.subtract); 
divide = findViewById(R.id.divide); 
point = findViewById(R.id.point); 
clear = findViewById(R.id.clear); 
equal = findViewById(R.id.equal); 
editTextText = findViewById(R.id.editTextText); 
// Number buttons 
one.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("1"); // Replace the operator with the number 
} else { 
editTextText.setText(editTextText.getText() + "1"); 
} 
}); 
two.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("2"); // Replace the operator with the number 
} else { 
editTextText.setText(editTextText.getText() + "2"); 
} 
}); 
three.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("3"); 
} else { 
editTextText.setText(editTextText.getText() + "3"); 
} 
}); 
four.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("4"); 
} else { 
editTextText.setText(editTextText.getText() + "4"); 
} 
}); 
five.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("5"); 
} else { 
editTextText.setText(editTextText.getText() + "5"); 
} 
}); 
six.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("6"); 
} else { 
editTextText.setText(editTextText.getText() + "6"); 
} 
}); 
seven.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("7"); 
} else { 
editTextText.setText(editTextText.getText() + "7"); 
} 
}); 
eight.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("8"); 
} else { 
editTextText.setText(editTextText.getText() + "8"); 
} 
}); 
nine.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("9"); 
} else { 
editTextText.setText(editTextText.getText() + "9"); 
} 
}); 
zero.setOnClickListener(v -> { 
if (editTextText.getText().toString().equals("+") || 
editTextText.getText().toString().equals("-") || 
editTextText.getText().toString().equals("*") || 
editTextText.getText().toString().equals("/")) { 
editTextText.setText("0"); 
} else { 
editTextText.setText(editTextText.getText() + "0"); 
} 
}); 
point.setOnClickListener(v -> { 
// Allow the user to add a decimal point only if there isn't one already in the number. 
if (!editTextText.getText().toString().contains(".")) { 
editTextText.setText(editTextText.getText() + "."); 
} 
}); 
// Clear button 
clear.setOnClickListener(v -> editTextText.setText("")); 
// Operation buttons 
add.setOnClickListener(v -> { 
if (!editTextText.getText().toString().isEmpty()) { 
mValue1 = Float.parseFloat(editTextText.getText() + ""); 
tAdd = true; 
editTextText.setText("+"); 
} 
}); 
multiply.setOnClickListener(v -> { 
if (!editTextText.getText().toString().isEmpty()) { 
mValue1 = Float.parseFloat(editTextText.getText() + ""); 
tMul = true; 
editTextText.setText("*"); 
} 
}); 
subtract.setOnClickListener(v -> { 
if (!editTextText.getText().toString().isEmpty()) { 
mValue1 = Float.parseFloat(editTextText.getText() + ""); 
tSub = true; 
editTextText.setText("-"); 
} 
}); 
divide.setOnClickListener(v -> { 
if (!editTextText.getText().toString().isEmpty()) { 
mValue1 = Float.parseFloat(editTextText.getText() + ""); 
tDiv = true; 
editTextText.setText("/"); 
} 
}); 
// Equal button 
equal.setOnClickListener(v -> { 
if (!editTextText.getText().toString().isEmpty()) { 
mValue2 = Float.parseFloat(editTextText.getText() + ""); 
if (tAdd) { 
editTextText.setText(String.valueOf(mValue1 + mValue2)); 
tAdd = false; 
} 
if (tSub) { 
editTextText.setText(String.valueOf(mValue1 - mValue2)); 
tSub = false; 
} 
if (tMul) { 
editTextText.setText(String.valueOf(mValue1 * mValue2)); 
tMul = false; 
} 
if (tDiv) { 
if (mValue2 != 0) { 
editTextText.setText(String.valueOf(mValue1 / mValue2)); 
} else { 
editTextText.setText("Error"); 
} 
tDiv = false; 
} 
} 
}); 
} 
}
