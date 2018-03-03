package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

public class Conversations extends AppCompatActivity {

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
    private ArrayList<String> AllConversations;
    private ArrayList<String> AllNames;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);


        AllConversations = new ArrayList<String>();


        mAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        String UID = mAuth.getCurrentUser().getUid();
        listview = findViewById(R.id.newListView);

        myRefAvailability = FirebaseDatabase.getInstance().getReference("Users");
        myRef = myRefAvailability.child(UID).child("Conversations");
        myRef.addValueEventListener(new ValueEventListener() {
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


                String [] convos = temp.split(",");
                for (int i = 1; i < convos.length; i++)
                {
                    AllConversations.add(convos[i]);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(Conversations.this, android.R.layout.simple_list_item_1, AllConversations);

                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                        if(temp != ", ")
                        {
                            String conversation = (listview.getItemAtPosition(position)).toString();
                            Intent gotoMessages = new Intent(Conversations.this, Messages.class);
                            gotoMessages.putExtra("conversation", conversation);
                            startActivity(gotoMessages);
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
        startActivity(new Intent(Conversations.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(Conversations.this, Main_Page2.class));

    }
}
