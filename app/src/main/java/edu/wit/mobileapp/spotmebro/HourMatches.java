package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HourMatches extends AppCompatActivity {


        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        // Database stuff:
        private FirebaseDatabase database;
        private DatabaseReference myRef;
        private DatabaseReference myTime;
        private DatabaseReference myRefUsers;
        private static final String TAG = "MyActivity";
        private RecyclerView recyclerview;
        private ListView listview;
        private ArrayList<String> entries;
        private ArrayList<String> AllNames;
        private ArrayList<String> AllUIDs;
        private String available;

        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener authStateListener;
        //
        //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hour_matches);


            mAuth = FirebaseAuth.getInstance();

            Bundle extras = getIntent().getExtras();
            if (extras != null)
            {
                available = extras.getString("timeset");
            }

            String [] parts = available.split(" ");
            String Day = parts[0];
            String Time = parts[1];
            String AMPM = parts[2];

            String finaltime = "0";
            String time = Time;

            if (AMPM.equalsIgnoreCase("AM")) {
                if (time == "12")
                {
                    Time = "0";
                }
                finaltime = Time;

            }
            else if (AMPM.equalsIgnoreCase("PM"))
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
            Time = finaltime;

            AllNames = new ArrayList<>();
            AllUIDs = new ArrayList<>();


            myTime = FirebaseDatabase.getInstance().getReference(Day).child(Time);
            myTime.addValueEventListener(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshots)
                {
                    listview = findViewById(R.id.newListView);


                    for (DataSnapshot ds : dataSnapshots.getChildren())
                    {
                        //try
                        //{
                            String Style = ds.child("Style").getValue().toString();
                            String Gender = ds.child("Gender").getValue().toString();
                            if(( MyApplication.Global_Style.equalsIgnoreCase(Style))&& ((MyApplication.Global_Preffered_Gender.equalsIgnoreCase(Gender))))
                            {
                                String name = ds.child("Email").getValue().toString();
                                String UIDs = ds.getKey();
                                AllNames.add(name);
                                AllUIDs.add(UIDs);
                            }
                            else if ( (MyApplication.Global_Preffered_Gender.equalsIgnoreCase("Both")) && ( MyApplication.Global_Style.equalsIgnoreCase(Style )))
                            {
                                String name = ds.child("Email").getValue().toString();
                                String UIDs = ds.getKey();
                                AllNames.add(name);
                                AllUIDs.add(UIDs);
                            }

                        //}
                        //catch (NullPointerException i)
                        //{
                        //    Log.v(TAG, "No values =" + i);
                        //}

                    }
                    if (AllNames.isEmpty())
                    {
                        AllNames.add("No Matches");

                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter(HourMatches.this, android.R.layout.simple_list_item_1, AllNames);

                    listview.setAdapter(adapter);

                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {

                            String Email_of_User = (listview.getItemAtPosition(position)).toString();
                            if ( Email_of_User.equalsIgnoreCase("No Matches"))
                            {

                            }
                            else
                            {
                                int index = 0;
                                for (int i = 0; i < AllNames.size(); i++) {
                                    if (Email_of_User.equalsIgnoreCase(AllNames.get(i))) {
                                        index = i;
                                    }
                                }

                                String profileUID = AllUIDs.get(index);
                                Intent gotoUserProfile = new Intent(HourMatches.this, Matches_Profile.class);
                                gotoUserProfile.putExtra("UID", profileUID);
                                startActivity(gotoUserProfile);
                            }

                        }
                    });

                }

                @Override
                public void onCancelled(DatabaseError databaseError)
                {

                }
            });








    }





    public void signOut(View view)
    {

        mAuth.signOut();
        startActivity(new Intent(HourMatches.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(HourMatches.this, Main_Page2.class));

    }

    public void GotoProfile(View view)
    {


    }















}

