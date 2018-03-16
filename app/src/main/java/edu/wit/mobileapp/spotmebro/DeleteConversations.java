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

public class DeleteConversations extends AppCompatActivity {

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
    private String conversationID1;
    private String conversationID2;
    private String otherUID;
    private String RemoveFromConversation;

    private String [] convos;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    //
    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_conversations);

        RemoveFromConversation = "";
        AllConversations = new ArrayList<String>();


        mAuth = FirebaseAuth.getInstance();


        mAuth = FirebaseAuth.getInstance();
        String UID = mAuth.getCurrentUser().getUid();
        listview = findViewById(R.id.newListView);

        myRef = FirebaseDatabase.getInstance().getReference("Messages");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String messagename = ds.getKey().toString();
                    String[] names = messagename.split("-");
                    if (names[0].equalsIgnoreCase(MyApplication.Global_Name) || names[1].equalsIgnoreCase(MyApplication.Global_Name)) {
                        if (names[0].equalsIgnoreCase(MyApplication.Global_Name)) {

                        } else {
                            AllConversations.add(names[0]);
                        }
                        if (names[1].equalsIgnoreCase(MyApplication.Global_Name)) {

                        } else {
                            AllConversations.add(names[1]);
                        }
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter(DeleteConversations.this, android.R.layout.simple_list_item_1, AllConversations);

                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
                {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {

                            String youremail = MyApplication.Global_Name.toString();
                            String other_email = (listview.getItemAtPosition(position)).toString();



                            conversationID1 = other_email + "-" + youremail;
                            conversationID2 =  youremail + "-" + other_email;
                            String yourUID = mAuth.getCurrentUser().getUid().toString();

                            try
                            {
                                myRef = FirebaseDatabase.getInstance().getReference("").child("Messages").child(conversationID1);
                                myRef.removeValue();
                            }
                            catch (RuntimeException e)
                            {

                            }


                            try
                            {
                                myRef = FirebaseDatabase.getInstance().getReference("").child("Messages").child(conversationID2);
                                myRef.removeValue();
                            }
                            catch(RuntimeException e)
                            {

                            }



                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        delete_user(RemoveFromConversation);




    }






    public void signOut(View view)
    {

        mAuth.signOut();
        startActivity(new Intent(DeleteConversations.this, Login.class));

    }

    public void GoToMain(View view)
    {
        startActivity(new Intent(DeleteConversations.this, Main_Page2.class));

    }

    public void GoToDelete(View view) {
        startActivity(new Intent(DeleteConversations.this, Conversations.class));

    }

    public void delete_user(final String name)
    {
        if(name!= "") {
            final String youremail = MyApplication.Global_Name.toString();
            myRef = FirebaseDatabase.getInstance().getReference("").child("Users");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        if (ds.child("Name").getValue().toString().equalsIgnoreCase(name)) {
                            temp = ds.child("Conversations").getValue().toString();
                            otherUID = ds.getKey().toString();
                            myRef = FirebaseDatabase.getInstance().getReference("").child("Users").child(otherUID).child("Conversations");
                            temp = temp.replace(("," + youremail), "");
                            myRef.setValue(temp);
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        RemoveFromConversation = "";

    }
}

