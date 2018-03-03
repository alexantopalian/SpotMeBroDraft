package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class Messages extends AppCompatActivity {

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myTime;
    private DatabaseReference myRefUsers;
    private DatabaseReference myRefAvailability;

    private RecyclerView recyclerview;
    private String conversationID;

    private ListView listview;
    private ArrayList<String> entries;
    private ArrayList<String> AllConversations;
    private ArrayList<String> AllNames;

    private EditText medittext;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        listview = (ListView) findViewById(R.id.newListView);

        mAuth = FirebaseAuth.getInstance();
        conversationID = " ";
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            conversationID = extras.getString("conversation");
        }
        myRef = FirebaseDatabase.getInstance().getReference("Messages").child(conversationID);
        myRef.addValueEventListener(new ValueEventListener() {
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
                       //error messages

                    }
                }
                ArrayAdapter<String> arrayAdapter;
                arrayAdapter = new ArrayAdapter<String>(Messages.this, android.R.layout.simple_list_item_1, entries);
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
        startActivity(new Intent(Messages.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(Messages.this, Main_Page2.class));

    }

    public void addMessage(View view) {
        medittext = (EditText) findViewById(R.id.newmessage);

        myRef.push().setValue(medittext.getText().toString());
    }
}
