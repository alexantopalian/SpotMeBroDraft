package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileEditor extends AppCompatActivity {

    private String availabilities;
    private EditText mName_input;
    private Spinner mPref_gender_input;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("");
    DatabaseReference myRef1 = database.getReference("");
    DatabaseReference myRef2 = database.getReference("");
    private FirebaseAuth mAuth;
    private String uourName;
    private ArrayList<String> AllTimes;
    private boolean isthere;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        mName_input = findViewById(R.id.your_name);
        mPref_gender_input = findViewById(R.id.Preferred_Gender);

        mAuth = FirebaseAuth.getInstance();


        String user = mAuth.getCurrentUser().getUid().toString();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(user);
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                String names = dataSnapshot.child("Name").getValue().toString();
                uourName = names;
                String pref_gend = dataSnapshot.child("Gender").getValue().toString();
                mName_input.setText(names);
                mPref_gender_input.setPrompt(pref_gend);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    public void submit_changes(View view)
    {
        mName_input = findViewById(R.id.your_name);
        mPref_gender_input = findViewById(R.id.Preferred_Gender);
        mAuth = FirebaseAuth.getInstance();
        final String user = mAuth.getCurrentUser().getUid().toString();

        myRef = FirebaseDatabase.getInstance().getReference("Users");
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    try
                    {
                        if( ds.child("Name").getValue().toString().equalsIgnoreCase(mName_input.getText().toString()))
                        {
                            isthere = true;
                        }
                    }
                    catch (NullPointerException e)
                    {

                    }
                }

                if (isthere == true)
                {
                    Toast.makeText(ProfileEditor.this, "Name is already used", Toast.LENGTH_LONG).show();
                }
                else {
                    myRef2 = FirebaseDatabase.getInstance().getReference("Messages");
                    myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot)
                        {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String messagename = ds.getKey().toString();
                                String[] names = messagename.split("-");
                                if (names[0].equalsIgnoreCase(MyApplication.Global_Name) || names[1].equalsIgnoreCase(MyApplication.Global_Name)) {
                                    myRef1 = FirebaseDatabase.getInstance().getReference("Messages").child(ds.getKey().toString());
                                    myRef1.removeValue();
                                }
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });
                    myRef.child(user).child("Gender").setValue(mPref_gender_input.getSelectedItem().toString());
                    myRef.child(user).child("Name").setValue(mName_input.getText().toString());


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public void gotoBacktoMain(View view)
    {
        startActivity(new Intent(ProfileEditor.this, Main_Page2.class));

    }
}
