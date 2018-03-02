package edu.wit.mobileapp.spotmebro;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Date_Adder extends AppCompatActivity {


    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("");
    private DatabaseReference myUser = database.getReference("");
    private DatabaseReference myxx = database.getReference("");
    private DatabaseReference mavailability = database.getReference("");

    private DatabaseReference myRefAvailability;


    private String temp;

    private ListView listview;
    private ArrayList<String> entries;
    private ArrayList<String> AllTimes;

    private String UID;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Widgets:
    private Button mback;
    private Spinner mDate_input;
    private Spinner mTime_input;
    private Spinner mAMPM_input;

    Calendar date;

    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Variables:
    private static final String TAG = "Date_Adder";
    private int time_input;


    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // onOverided functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__adder);


        AllTimes = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        UID = mAuth.getCurrentUser().getUid();
        listview = findViewById(R.id.newListView);

        myRefAvailability = FirebaseDatabase.getInstance().getReference("Users").child(UID).child("Availability");
        myRefAvailability.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                AllTimes.clear();
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
                    AllTimes.add(available[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(Date_Adder.this, android.R.layout.simple_list_item_1, AllTimes);

                listview.setAdapter(adapter);


                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (temp != ", ") {


                            String timeset = (listview.getItemAtPosition(position)).toString();
                            String[] available = timeset.split(",");


                            String[] parts = available[0].split(" ");
                            String Day = parts[0];
                            String Time = parts[1];
                            String AMPM = parts[2];

                            String finaltime = "0";
                            String time = Time;

                            if (AMPM.equalsIgnoreCase("AM")) {
                                if (time == "12") {
                                    Time = "0";
                                }
                                finaltime=Time;

                            } else if (AMPM.equalsIgnoreCase("PM")) {
                                switch (time) {
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


                            myRef = FirebaseDatabase.getInstance().getReference("").child(Day).child(Time).child(UID);
                            myRef.removeValue();

                            temp = temp.replace(("," + timeset), "");
                            myRef = FirebaseDatabase.getInstance().getReference("Users").child(UID).child("Availability");
                            myRef.setValue(temp);


                        }

                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // home built functions


    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(Date_Adder.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(Date_Adder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        Log.v(TAG, "The chosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void signOut(View view) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(Date_Adder.this, Login.class));

    }

    public void GoToMain(View view) {
        startActivity(new Intent(Date_Adder.this, Main_Page2.class));
    }


    public void Enter_in_Datetime(View view) {
        mTime_input = findViewById(R.id.Time_input);
        mDate_input = findViewById(R.id.Day_input);
        mAMPM_input = findViewById(R.id.AMPM_input);

        String Time = mTime_input.getSelectedItem().toString();
        final String Day = mDate_input.getSelectedItem().toString();
        final String AMPM = mAMPM_input.getSelectedItem().toString();

        String finaltime = "0";
        String time = Time;

        if (AMPM.equalsIgnoreCase("AM"))
        {
            if (time == "12")
            {
                finaltime = "0";
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
        else
        {
            finaltime = Time;
        }
        Time = finaltime;
        final String FinalTime = Time;

        mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getUid();
        final String email = mAuth.getCurrentUser().getEmail().toLowerCase();


        myRef = database.getReference(Day).child(FinalTime);
        mavailability = myRef.child(user);
        myxx = database.getReference("Users").child(user);
        myUser = myxx.child("Availability");
        // just want the value at availability


        myUser.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                String availabilities;
                if(dataSnapshot.getValue() == null)
                {
                    availabilities = "";
                }
                else
                {
                    availabilities = dataSnapshot.getValue().toString();

                }

                if (availabilities.contains("," + Day + " " + FinalTime + " " + AMPM + " "))
                {
                    Toast.makeText(Date_Adder.this, "Already an added availability", Toast.LENGTH_LONG).show();
                }
                else
                {
                    mavailability.child("Email").setValue(email);
                    mavailability.child("Gender").setValue(MyApplication.Global_Gender);
                    mavailability.child("Style").setValue(MyApplication.Global_Style);
                    availabilities = availabilities + "," + Day + " " + FinalTime + " " + AMPM + " ";
                    myUser.setValue(availabilities);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });



    }



    }


