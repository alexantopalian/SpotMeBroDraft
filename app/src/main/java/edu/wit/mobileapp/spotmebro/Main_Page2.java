package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main_Page2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Database stuff:
    FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private DatabaseReference myRefAvailability;
    private FirebaseAuth.AuthStateListener authStateListener;
    //

    private TextView mUidNav;
    //----------------------------------------------------------------------------------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__page2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        //mAuth.signOut();
        String tempUser = "";
        final String user = mAuth.getCurrentUser().getUid().toString();
        //mUidNav = findViewById(R.id.UIDNavTex);
        //mUidNav.append(user);


        //here we could add a gender based image and other be like a tank...

        myRef = FirebaseDatabase.getInstance().getReference("Users").child(user);
        // set listener to grab users preferences.
        myRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                MyApplication.Global_Gender = dataSnapshot.child("Gender").getValue().toString();
                MyApplication.Global_Style = dataSnapshot.child("Preferences").child("Style").getValue().toString();
                MyApplication.Global_Preffered_Gender = dataSnapshot.child("Preferences").child("Preferred_Gender").getValue().toString();

            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main__page2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Matches)
        {
            startActivity(new Intent(Main_Page2.this, Matches_Page.class));
        } else if (id == R.id.PreferenceEditor)
        {
            startActivity(new Intent(Main_Page2.this, PreferenceEditor.class));
        } else if (id == R.id.Profile)
        {
            startActivity(new Intent(Main_Page2.this, User_Profile.class));
        } else if (id == R.id.DateAdder)
        {
            startActivity(new Intent(Main_Page2.this, Date_Adder.class));
        } else if (id == R.id.ContactUs)
        {
            startActivity(new Intent(Main_Page2.this, ContactUs.class));
        }else if (id == R.id.Conversations)
        {
            startActivity(new Intent(Main_Page2.this, Conversations.class));
        } else if (id == R.id.SignOut)
        {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            startActivity(new Intent(Main_Page2.this, Login.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
