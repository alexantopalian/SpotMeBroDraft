package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class Matches_Profile extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myRef2;
    private DatabaseReference myRef3;

    private TextView mEmailOutput  ;
    private TextView mSecurityOutput  ;
    private TextView mAnswerOutput  ;
    private TextView mAvailabilityOutput  ;
    private TextView mConversationOutput ;
    private TextView mNameOutput ;
    private TextView mGenderOutput ;



    private DatabaseReference myRefUsers;
    private  String UID;

    private static final String TAG = "MyActivity";


    private ListView listview;
    private ArrayList<String> entries;
    private String formattedDate;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches__profile);
        mAuth = FirebaseAuth.getInstance();

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd_MMM_yyyy");
        formattedDate = df.format(c);

        //mAnswerOutput = (TextView) findViewById(R.id.Answer_output);
        //mAvailabilityOutput = (TextView) findViewById(R.id.Availability_output);
        //mConversationOutput = (TextView) findViewById(R.id.Conversation_output);
        mEmailOutput = (TextView) findViewById(R.id.Email_output);
        //mSecurityOutput = (TextView) findViewById(R.id.Security_output);
        mNameOutput = (TextView) findViewById(R.id.Name_output);
        mGenderOutput = (TextView) findViewById(R.id.Gender_output);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            UID = extras.getString("UID");
        }

        myRef = FirebaseDatabase.getInstance().getReference("Users");
        myRefUsers = myRef.child(UID);
        listview = findViewById(R.id.newListView);


        myRefUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                //mAnswerOutput.setText(dataSnapshot.child("Answer").getValue().toString());
                mEmailOutput.setText(dataSnapshot.child("Email").getValue().toString());
                //mSecurityOutput.setText(dataSnapshot.child("Security").getValue().toString());
                mNameOutput.setText(dataSnapshot.child("Name").getValue().toString());
                mGenderOutput.setText(dataSnapshot.child("Gender").getValue().toString());
                /*
                try {
                    mAvailabilityOutput.setText(dataSnapshot.child("Availability").getValue().toString());
                }
                catch (NullPointerException i){
                    mAvailabilityOutput.setText("no availabilities");
                }

                try {
                    mConversationOutput.setText(dataSnapshot.child("Conversations").getValue().toString());
                }
                catch (NullPointerException i){
                    mConversationOutput.setText("no conversations");
                }
                */


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    public void signOut(View view)
    {

        mAuth.signOut();
        startActivity(new Intent(Matches_Profile.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(Matches_Profile.this, Main_Page2.class));

    }

    public void GotoProfile(View view)
    {


    }

    public void newconvo(View view) {
        final String othersUID = UID;
        final String yourUID = mAuth.getUid().toString();
        final String otheremail = mNameOutput.getText().toString();
        final String youremail = MyApplication.Global_Name.toString();
        final String convo1 = (otheremail + '-' + youremail);
        final String convo2 = (youremail + '-' + otheremail);
        final String otherstuff = otheremail+"-"+othersUID;
        final String yourstuff = youremail+"-"+yourUID;

        myRef= FirebaseDatabase.getInstance().getReference("Messages");
        myRef.child(' '+ otheremail +'-' + youremail ).child("MessageList").push().setValue("New Message"+"-"+MyApplication.Global_Name+"-"+formattedDate);

        /*


        try
        {
            myRef2 = FirebaseDatabase.getInstance().getReference("Users").child(othersUID);
            myRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    try
                    {
                        String x = dataSnapshot.child("Conversations").getValue().toString();
                        if (x.contains(yourstuff))
                        {
                            //toast already conversation
                        }
                        else
                        {
                            myRef2.child("Conversations").setValue(dataSnapshot.child("Conversations").getValue().toString() + ", " + yourstuff);
                        }
                    }
                    catch(NullPointerException i)
                    {
                        myRef2.child("Conversations").setValue(", "+yourstuff);
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        catch (NullPointerException e)
        {
            database.getReference("Users").child(othersUID).child("Conversations").setValue(", "+yourstuff);
        }


        try
        {
            myRef3 = FirebaseDatabase.getInstance().getReference("Users").child(yourUID);
            myRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    try
                    {
                        String x = dataSnapshot.child("Conversations").getValue().toString();
                        if (x.contains(otherstuff)) {
                            //toast already conversation
                        } else {
                            myRef3.child("Conversations").setValue(dataSnapshot.child("Conversations").getValue().toString() + ", " + otherstuff);
                        }
                    }
                    catch(NullPointerException i)
                    {
                        //myRef = database.getReference("Users").child(yourUID);
                        myRef3.child("Conversations").setValue(", "+otherstuff);

                        //database.getReference("User").child(yourUID).child("Conversations").setValue(", "+ convo1);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        catch(NullPointerException e)
        {
            database.getReference("Users").child(yourUID).child("Conversations").setValue(", "+ otherstuff);
        }
*/

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
