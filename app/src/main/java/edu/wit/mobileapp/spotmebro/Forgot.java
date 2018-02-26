package edu.wit.mobileapp.spotmebro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Forgot extends AppCompatActivity {


    FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private DatabaseReference myRefAvailability;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ArrayList<String> entries;
    private EditText mEmail_input;
    private TextView msecques;
    private String answer;
    private EditText mAnswer_input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        mEmail_input = (EditText) findViewById(R.id.Email_input);
        msecques = (TextView) findViewById(R.id.SecQuesView);
        answer = "  ";
        mAnswer_input = (EditText) findViewById(R.id.Answer_input);

    }

    public void RetrieveSecurityQuestion(View view)
    {
        final String Email = mEmail_input.getText().toString().toLowerCase();

        myRef = FirebaseDatabase.getInstance().getReference("Users");
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while (iterator.hasNext()) {
                    DataSnapshot s = iterator.next();
                    String value = s.child("Email").getValue().toString().toLowerCase();
                    if(value.compareToIgnoreCase(Email) == 0 )
                    {
                        msecques.setText(s.child("Security").getValue().toString());
                        answer = s.child("Answer").getValue().toString().toLowerCase();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    public void validateAnswer(View view)
    {
        final String Email = mEmail_input.getText().toString();
        final String answergiven = mAnswer_input.getText().toString().toLowerCase();
        if(answergiven.compareToIgnoreCase(answer) == 0)
        {
            mAuth.getInstance().sendPasswordResetEmail(Email);
        }
        else
        {
            Toast.makeText(Forgot.this, "Wrong Answer", Toast.LENGTH_LONG).show();
        }
    }
}
