package com.example.mainactivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;

public class ProfileActivity extends AppCompatActivity {
    // Initialize variables
    private ImageView ivImage;
    private TextView tvName;
    Button submit;
    private Button dateButton;
    TextView statusname;
    //private Button btLogout;
    String name;
    private FirebaseAuth firebaseAuth;
    String selectedDate="select date";
    String welcome="Welcome";
    Intent intent;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Assign variables
        ivImage = findViewById(R.id.ivimage);
        tvName = findViewById(R.id.tvname);
       // statusname=findViewById(R.id.statusname);
        dateButton = findViewById(R.id.dateButton);
      // btLogout = findViewById(R.id.btlogout);
       submit=findViewById(R.id.submit);
       // Button navigate=findViewById(R.id.navigate);
        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        String[] genders = {"Male", "Female", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        genderSpinner.setAdapter(adapter);
         intent=new Intent(ProfileActivity.this, dashboard.class);










        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize Firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            // When firebase user is not equal to null set image on image view
            Glide.with(ProfileActivity.this).load(firebaseUser.getPhotoUrl()).into(ivImage);
            // Set name on text view
            tvName.setText(   firebaseUser.getDisplayName()  );
            name=firebaseUser.getDisplayName();



            //welcome=welcome+name;
           // statusname.setText(welcome+"   "+name+"!");


        }

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);

//        btLogout.setOnClickListener(view -> {
//            // Sign out from Google
//            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    // Check condition
//                    if (task.isSuccessful()) {
//                        // When task is successful sign out from Firebase
//                        firebaseAuth.signOut();
//                        // Display Toast
//                        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
//                        // Finish activity
//                        finish();
//                    }
//                }
//            });
//        });
        dateButton.setOnClickListener(v -> showDatePickerDialog());
    }
    private void showDatePickerDialog() {

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> {
                    // Handle the date selection
                    selectedDate = dayOfMonth+" " + "/" +" " +(month1 + 1) + " "+"/" +" "+ year1;
                    dateButton.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_MONTH, -60);


        datePickerDialog.show();
        submit.setOnClickListener(v -> Toast.makeText(ProfileActivity.this,"Your Profile has been Updated",Toast.LENGTH_SHORT).show());
        submit.setOnClickListener(v -> startActivity(intent));

    }
    

    }



