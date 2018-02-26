package edu.wit.mobileapp.spotmebro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreferenceEditor extends AppCompatActivity {

    private String availabilities;
    private Spinner mStyle_input;
    private Spinner mPref_gender_input;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("");
    private FirebaseAuth mAuth;
    private ArrayList<String> AllTimes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_editor);
        mStyle_input = (Spinner) findViewById(R.id.Style_Input);
        mPref_gender_input = (Spinner) findViewById(R.id.Preferred_Gender);

        mAuth = FirebaseAuth.getInstance();
        availabilities = " ";

        String user = mAuth.getCurrentUser().getUid().toString();
        myRef = FirebaseDatabase.getInstance().getReference("Users").child(user);
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                availabilities = dataSnapshot.child("Availability").getValue().toString();
                String style = dataSnapshot.child("Preferences").child("Style").getValue().toString();
                String pref_gend = dataSnapshot.child("Preferences").child("Preferred_Gender").getValue().toString();
                mStyle_input.setPrompt(style);
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
        mStyle_input = (Spinner) findViewById(R.id.Style_Input);
        mPref_gender_input = (Spinner) findViewById(R.id.Preferred_Gender);
        mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid().toString();

        String [] available = availabilities.split(",");
        for (int i = 1; i < available.length; i++)
        {



            String [] parts = available[i].split(" ");
            String Day = parts[0];
            String Time = parts[1];
            String AMPM = parts[2];

            String finaltime = "0";
            String time = Time;

            if (AMPM == "AM") {
                if (time == "12")
                {
                    Time = "0";
                }

            }
            else if (AMPM == "PM")
            {
                switch (time)
                {
                    case "1":
                        finaltime = "13";
                        break;
                    case "2":
                        finaltime = "14";
                        break;
                    case "3":
                        finaltime = "15";
                        break;
                    case "4":
                        finaltime = "16";
                        break;
                    case "5":
                        finaltime = "17";
                        break;
                    case "6":
                        finaltime = "18";
                        break;
                    case "7":
                        finaltime = "19";
                        break;
                    case "8":
                        finaltime = "20";
                        break;
                    case "9":
                        finaltime = "21";
                        break;
                    case "10":
                        finaltime = "22";
                        break;
                    case "11":
                        finaltime = "23";
                        break;
                    case "12":
                        finaltime = "12";
                        break;

                }
                Time = finaltime;
            }


            myRef = FirebaseDatabase.getInstance().getReference("").child(Day).child(Time).child(user);
            // set listener to grab users preferences.
            myRef.child("Style").setValue(mStyle_input.getSelectedItem().toString());


        }

        myRef = FirebaseDatabase.getInstance().getReference("Users").child(user).child("Preferences");
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myRef.child("Preferred_Gender").setValue(mPref_gender_input.getSelectedItem().toString());
                myRef.child("Style").setValue(mStyle_input.getSelectedItem().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
