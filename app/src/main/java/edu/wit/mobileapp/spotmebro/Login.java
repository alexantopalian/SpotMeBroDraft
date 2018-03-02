package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity
{
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    FirebaseDatabase database;
    DatabaseReference myRef;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Widget Declarations
    private EditText mUser_Email;
    private EditText mUser_Password;

    private Button mLogin_Button;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Variable Declarations
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUser_Email = findViewById(R.id.User_Email);
        mUser_Password = findViewById(R.id.User_Password);


        mAuth = FirebaseAuth.getInstance();
        authStateListener= new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {
                if (firebaseAuth.getCurrentUser()!=null)
                {

                    Intent intent = new Intent(Login.this,Main_Page2.class);
                    startActivity(intent);
                }

            }
        };


    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);

    }

    public void Login_Button_Clicked(View view)
    {
        String email =  mUser_Email.getText().toString();
        String pass = mUser_Password.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"Enter both Values", Toast.LENGTH_LONG).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(!task.isSuccessful())
                    {
                        Toast.makeText(Login.this,"Incorrect username and password",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    public void Signup_Button_Clicked(View view)
    {
        Intent intent = new Intent(Login.this,Sign_up.class);
        startActivity(intent);
    }

    public void Forgot_Button_Clicked(View view) {
        Intent intent = new Intent(Login.this,Forgot.class);
        startActivity(intent);

    }
}
