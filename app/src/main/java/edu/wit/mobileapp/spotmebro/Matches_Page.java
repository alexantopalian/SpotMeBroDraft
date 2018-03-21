package edu.wit.mobileapp.spotmebro;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static android.app.PendingIntent.getActivity;

public class Matches_Page extends AppCompatActivity
{
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myTime;
    private DatabaseReference myRefUsers;
    private DatabaseReference myRefAvailability;

    private RecyclerView recyclerview;
    private String temp;

    private ListView listview;
    private ArrayList<String> entries;
    private ArrayList<String> AllTimes;
    private ArrayList<String> AllNames;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches__page);

        AllTimes = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        String UID = mAuth.getCurrentUser().getUid();
        listview = findViewById(R.id.newListView);

        myRefAvailability = FirebaseDatabase.getInstance().getReference("Users").child(UID).child("Availability");
        myRefAvailability.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                try
                {
                    temp = dataSnapshot.getValue().toString();
                }
                catch (NullPointerException i)
                {
                    temp = ", ";
                }
                String [] available = temp.split(",");
                for (int i = 1; i < available.length; i++)
                {
                    String[] parts = available[i].split(" ");
                    String Time = parts[1];
                    switch (Time)
                    {
                        case "13":
                            parts[1] = "1";
                            break;
                        case "14":
                            parts[1] = "2";
                            break;
                        case "15":
                            parts[1] = "3";
                            break;
                        case "16":
                            parts[1] = "4";
                            break;
                        case "17":
                            parts[1] = "5";
                            break;
                        case "18":
                            parts[1] = "6";
                            break;
                        case "19":
                            parts[1] = "7";
                            break;
                        case "20":
                            parts[1] = "8";
                            break;
                        case "21":
                            parts[1] = "9";
                            break;
                        case "22":
                            parts[1] = "10";
                            break;
                        case "23":
                            parts[1] = "11";
                            break;
                        case "12":
                            parts[1] = "12";
                            break;

                    }
                    available[i] = parts[0]+ " "+parts[1]+ " "+parts[2];

                    AllTimes.add(available[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(Matches_Page.this, android.R.layout.simple_list_item_1, AllTimes);

                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(temp != ", ")
                        {
                            String timeset = (listview.getItemAtPosition(position)).toString();
                            Intent gotoTimeMatches = new Intent(Matches_Page.this, HourMatches.class);
                            gotoTimeMatches.putExtra("timeset",timeset);
                            startActivity(gotoTimeMatches);

                        }

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }





    public void signOut(View view)
    {

        mAuth.signOut();
        startActivity(new Intent(Matches_Page.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(Matches_Page.this, Main_Page2.class));

    }

    public void GotoProfile(View view)
    {


    }











    static public class User
    {
        public String getSecurity() {
            return Security;
        }

        public void setSecurity(String Security) {
            this.Security = Security;
        }

        public String getAnswer() {
            return Answer;
        }

        public void setAnswer(String Answer) {
            this.Answer = Answer;
        }

        public String getStyle() {
            return Style;
        }

        public void setStyle(String Style) {
            this.Style = Style;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        private String Email;
        private String Security;
        private String Answer;
        private String Style;

        public User() {
        }
        public User(String Security, String Answer, String Style, String Email)
        {
            this.Answer = Answer;
            this.Security = Security;
            this.Style = Style;
            this.Email = Email;
        }


    }




}
