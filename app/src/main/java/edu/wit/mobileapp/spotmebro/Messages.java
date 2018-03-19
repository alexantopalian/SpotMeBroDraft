package edu.wit.mobileapp.spotmebro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;

public class Messages extends AppCompatActivity {

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private DatabaseReference myTime;
    private DatabaseReference myRefUsers;
    private DatabaseReference myRefAvailability;

    private RecyclerView recyclerview;
    private String conversationID1;
    private String conversationID2;
    private String conversation;
    private ListView listview;
    private ArrayList<String> entries;
    private ArrayList<String> AllConversations;
    private ArrayList<String> AllNames;

    private EditText medittext;
    private String formattedDate;

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
        String youremail = MyApplication.Global_Name.toString();
        conversation = " ";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            conversation = extras.getString("conversation");
        }

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd_MMM_yyyy");
        formattedDate = df.format(c);


        conversationID1 = conversation + "-" + youremail;
        conversationID2 =  youremail + "-" + conversation;


        try {
            myRef = FirebaseDatabase.getInstance().getReference("Messages").child(conversationID2).child("MessageList");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<ListItem> list = new ArrayList<ListItem>();
                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                    while (iterator.hasNext()) {
                        try {
                            String value = iterator.next().getValue(String.class);
                            String [] components = value.split("-");
                            ListItem item1 = new ListItem();
                            item1.Name = components[1];
                            item1.Date = components[2];
                            item1.Message = components[0];
                            list.add(item1);
                        } catch (DatabaseException e) {
                            //error messages

                        }
                    }
                    GridItemAdapter adapter;
                    adapter = new GridItemAdapter(Messages.this, 0, list);
                    listview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        catch (NullPointerException e)
        {
            myRef = FirebaseDatabase.getInstance().getReference("Messages").child(conversationID1).child("MessageList");
        }

        try {
            myRef = FirebaseDatabase.getInstance().getReference("Messages").child(conversationID1).child("MessageList");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<ListItem> list = new ArrayList<ListItem>();
                    Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                    Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                    while (iterator.hasNext())
                    {
                        try {
                            String value = iterator.next().getValue(String.class);
                            String [] components = value.split("-");
                            ListItem item1 = new ListItem();
                            item1.Name = components[1];
                            item1.Date = components[2];
                            item1.Message = components[0];
                            list.add(item1);

                        } catch (DatabaseException e) {
                            //error messages

                        }
                    }
                    GridItemAdapter adapter;
                    adapter = new GridItemAdapter(Messages.this, 0, list);
                    listview.setAdapter(adapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        catch (NullPointerException e)
        {
            myRef = FirebaseDatabase.getInstance().getReference("Messages").child(conversationID2).child("MessageList");
        }
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
        myRef.push().setValue(medittext.getText().toString()+"-"+MyApplication.Global_Name+"-"+formattedDate);
    }

    public class GridItemAdapter extends ArrayAdapter<ListItem> {
        private LayoutInflater mInflater;

        public GridItemAdapter (Context context, int rid, List<ListItem> list)
        {
            super(context, rid, list);
            mInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }

        public View getView(int position, View convertview, ViewGroup parent) {

            //Retrieve data
            ListItem item = (ListItem) getItem(position);

            // Use layout file to generate View
            View view = mInflater.inflate(R.layout.list_item, null);

            //set image
            TextView name;
            name = (TextView) view.findViewById(R.id.name);
            name.setText(item.Name);

            // Set user name
            TextView date;
            date = (TextView) view.findViewById(R.id.date);
            date.setText(item.Date);

            //Set comment
            TextView message;
            message = (TextView) view.findViewById(R.id.message);
            message.setText(item.Message);

            return view;
        }


    }



    public class ListItem{
        public String Name;
        public String Date;
        public String Message;
    }
}
