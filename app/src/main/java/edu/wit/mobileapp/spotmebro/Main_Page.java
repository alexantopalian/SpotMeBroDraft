package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main_Page extends AppCompatActivity
{
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private DatabaseReference myRefAvailability;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //----------------------------------------------------------------------------------------------------------------------------------------------------------
    // Variables

    //
    //----------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page);
        mAuth = FirebaseAuth.getInstance();


        String user = mAuth.getCurrentUser().getUid().toString();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(user);
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                MyApplication.Global_Gender = dataSnapshot.child("Gender").getValue().toString();
                MyApplication.Global_Style = dataSnapshot.child("Preferences").child("Style").getValue().toString();
                MyApplication.Global_Preffered_Gender = dataSnapshot.child("Preferences").child("Preferred_Gender").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    public void signOut(View view)
    {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(Main_Page.this, Login.class));
    }

    public void GotoMatches(View view)
    {
        startActivity(new Intent(Main_Page.this, Matches_Page.class));
    }

    public void GotoEditor(View view)
    {
        startActivity(new Intent(Main_Page.this, PreferenceEditor.class));

    }

    public void GotoProfile(View view)
    {
        startActivity(new Intent(Main_Page.this, User_Profile.class));

    }

    public void GotoDateAdder(View view)
    {
        startActivity(new Intent(Main_Page.this, Date_Adder.class));
    }
}
