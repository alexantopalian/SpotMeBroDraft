package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class Matches_Profile extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myRefUsers;
    private  String UID;

    private static final String TAG = "MyActivity";


    private ListView listview;
    private ArrayList<String> entries;


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
                entries = new ArrayList<>();
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while(iterator.hasNext())
                {
                    try
                    {
                        String value = iterator.next().getValue(String.class);
                        entries.add(value);
                    }
                    catch (DatabaseException e)
                    {
                        Log.v(TAG, "preferences=" + e);

                    }
                }
                ArrayAdapter<String> arrayAdapter;
                arrayAdapter = new ArrayAdapter<String>(Matches_Profile.this, android.R.layout.simple_list_item_1, entries);
                listview.setAdapter(arrayAdapter);
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
        final String convo1 = (othersUID + '-' + yourUID);
        final String convo2 = (yourUID + '-' + othersUID);

        myRef = FirebaseDatabase.getInstance().getReference("Messages");
        myRef.child(' '+ othersUID + '-' + yourUID).push().setValue("New Message");
        try
        {
            myRef = FirebaseDatabase.getInstance().getReference("Users").child(othersUID);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    try
                    {
                        String x = dataSnapshot.child("Conversations").getValue().toString();
                        if (x.contains(convo1) || x.contains(convo2))
                        {
                            //toast already conversation
                        }
                        else
                        {
                            myRef.child("Conversations").setValue(dataSnapshot.child("Conversations").getValue().toString() + ", " + convo1);
                        }
                    }
                    catch(NullPointerException i)
                    {
                        myRef.child("Conversations").setValue(", "+convo1);
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        catch (NullPointerException e)
        {
            database.getReference("Users").child(othersUID).child("Conversations").setValue(", "+convo1);

        }

        try
        {
            myRef = FirebaseDatabase.getInstance().getReference("Users").child(yourUID);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    try
                    {
                        String x = dataSnapshot.child("Conversations").getValue().toString();
                        if (x.contains(convo1) || x.contains(convo2)) {
                            //toast already conversation
                        } else {
                            myRef.child("Conversations").setValue(dataSnapshot.child("Conversations").getValue().toString() + ", " + convo1);
                        }
                    }
                    catch(NullPointerException i)
                    {
                        //myRef = database.getReference("Users").child(yourUID);
                        myRef.child("Conversations").setValue(", "+convo1);

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
            database.getReference("Users").child(yourUID).child("Conversations").setValue(", "+ convo1);
        }


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
