package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewSwitcher;

public class Profile_Preference_Editor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__preference__editor);

        //Returns back to the Main Page
        Button back_btn = findViewById(R.id.back);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile_Preference_Editor.this, Main_Page.class);
                startActivity(intent);
            }
        });

        ImageButton rightArrow = findViewById(R.id.right_arrow);
        ImageButton leftArrow = findViewById(R.id.left_arrow);

        final ViewSwitcher switchView = findViewById(R.id.profile_preference_flipper);

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        switchView.setInAnimation(in);
        switchView.setOutAnimation(out);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView.showNext();
            }
        });

        //Flips the view back to Profile Editor
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView.showNext();
            }
        });
    }
}
