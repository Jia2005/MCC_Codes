package com.example.form; 
 
import android.os.Bundle; 
import android.view.View; 
import android.widget.Button; 
import android.widget.EditText; 
import android.widget.RadioButton; 
import android.widget.RadioGroup; 
import android.widget.Toast; 
import androidx.appcompat.app.AlertDialog; 
import androidx.appcompat.app.AppCompatActivity; 
import android.util.Patterns; 
 
import com.google.firebase.firestore.FirebaseFirestore; 
import java.util.HashMap; 
import java.util.Map; 
 
public class MainActivity extends AppCompatActivity { 
 
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, 
editTextAddress, editTextCountry; 
    private RadioGroup radioGroupGender; 
    private Button buttonSubmit; 
    private FirebaseFirestore db; 
 
    @Override 
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
 
        // Initialize Firestore 
        db = FirebaseFirestore.getInstance(); 
 
        editTextFirstName = findViewById(R.id.editTextFirstName); 
        editTextLastName = findViewById(R.id.editTextLastName); 
        editTextEmail = findViewById(R.id.editTextEmail); 
        editTextPhone = findViewById(R.id.editTextPhone); 
        editTextAddress = findViewById(R.id.editTextAddress); 
        editTextCountry = findViewById(R.id.editTextCountry); 
        radioGroupGender = findViewById(R.id.radioGroupGender); 
        buttonSubmit = findViewById(R.id.buttonSubmit); 
 
        buttonSubmit.setOnClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
                String firstName = editTextFirstName.getText().toString().trim(); 
                String lastName = editTextLastName.getText().toString().trim(); 
                String email = editTextEmail.getText().toString().trim(); 
                String phone = editTextPhone.getText().toString().trim(); 
                String address = editTextAddress.getText().toString().trim(); 
                String country = editTextCountry.getText().toString().trim(); 
 
                int selectedGenderId = radioGroupGender.getCheckedRadioButtonId(); 
                RadioButton selectedGender = findViewById(selectedGenderId); 
                String gender = selectedGender != null ? selectedGender.getText().toString() : ""; 
 
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || 
                        phone.isEmpty() || address.isEmpty() || country.isEmpty() || gender.isEmpty()) 
{ 
                    Toast.makeText(MainActivity.this, "All fields are required", 
Toast.LENGTH_SHORT).show(); 
                    return; 
                } 
 
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { 
                    Toast.makeText(MainActivity.this, "Please enter a valid email address", 
Toast.LENGTH_SHORT).show(); 
                    return; 
                } 
 
                if (!phone.matches("\\d{10,}")) { 
                    Toast.makeText(MainActivity.this, "Please enter a valid phone number 
(minimum 10 digits)", Toast.LENGTH_SHORT).show(); 
                    return; 
                } 
 
                String formData = String.format( 
                        "Form Details:\n\n" + 
                                "First Name: %s\n" + 
                                "Last Name: %s\n" + 
                                "Email: %s\n" + 
                                "Phone: %s\n" + 
                                "Address: %s\n" + 
                                "Country: %s\n" + 
                                "Gender: %s", 
                        firstName, lastName, email, phone, address, country, gender 
                ); 
 
                new AlertDialog.Builder(MainActivity.this) 
                        .setTitle("Form Details") 
                        .setMessage(formData) 
                        .setPositiveButton("OK", (dialog, which) -> saveToFirestore(firstName, 
lastName, email, phone, address, country, gender)) 
                        .show(); 
            } 
        }); 
    } 
 
    private void saveToFirestore(String firstName, String lastName, String email, String 
phone, String address, String country, String gender) { 
        Map<String, Object> user = new HashMap<>(); 
        user.put("firstName", firstName); 
        user.put("lastName", lastName); 
        user.put("email", email); 
        user.put("phone", phone); 
        user.put("address", address); 
        user.put("country", country); 
        user.put("gender", gender); 
 
        db.collection("users") 
                .add(user) 
                .addOnSuccessListener(documentReference -> Toast.makeText(MainActivity.this, 
"Data saved successfully!", Toast.LENGTH_SHORT).show()) 
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Error saving data: " 
+ e.getMessage(), Toast.LENGTH_SHORT).show()); 
    } 
}
