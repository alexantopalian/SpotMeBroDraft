package edu.wit.mobileapp.spotmebro;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void b1(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to find matches?");
        mtext1.setText("Make sure you have enter in your availabilities. (If no availabilities has been enter, you may follow the 'How to add availabilities' section for help.)");
        mtext2.setText("Click on \"Go to your Matches\" to find your matches.");
        mtext3.setText("Select one of your enter availabilities you wish to find a partner for.");
        mtext4.setText("Select a listed match and their profile will be shown.");
        mtext5.setText("You may start a conversation with the matched user to schedule a workout session.");



        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void b2(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to add availabilities?");
        mtext1.setText("Click on \"Select your Availability\" to enter in availabilities.");
        mtext2.setText("Choose a day of the week, hour, and the meridiem (AM/PM).");
        mtext3.setText("Once the availability has been submitted, it was be displayed below.");
        mtext4.setText("");
        mtext5.setText("");
        mtext6.setText("");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b3(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to remove availabilities?");
        mtext1.setText("Click on \"Select your Availability\" to remove availabilities.");
        mtext2.setText("You may click on a displayed availability you wish to delete.");
        mtext3.setText("");
        mtext4.setText("");
        mtext5.setText("");
        mtext6.setText("");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b4(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to edit preferences?");
        mtext1.setText("Click on \"Go to your Preference Editor\" to edit preferences.");
        mtext2.setText("You may changed your preferred partner's gender and preferred style.");
        mtext3.setText("Once submit has been clicked, your preferences has been changed.");
        mtext4.setText("");
        mtext5.setText("");
        mtext6.setText("");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b5(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to view/edit profile?");
        mtext1.setText("Click on \"Go to your Profile\" to view or edit your profile.");
        mtext2.setText("The profile information will be displayed upon entering the page.");
        mtext3.setText("To edit your profile name or gender, select \"Edit Profile\".");
        mtext4.setText("Once you finish editing, select \"Submit\" and changes will be made.");
        mtext5.setText("");
        mtext6.setText("");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b6(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to create a conversation?");
        mtext1.setText("Click on \"Go to your matches\" to select your partner.");
        mtext2.setText("Select the availability that both you and your partner has entered.");
        mtext3.setText("Click on their email, and their profile will appear.");
        mtext4.setText("Select the \"Create Conversation\" button to begin the conversation.");
        mtext5.setText("Go back to the main menu.");
        mtext6.setText("Click Conversations to view the newly created conversation.");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b7(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("How to delete a conversation?");
        mtext1.setText("Click on \"Conversations\".");
        mtext2.setText("Select the \"Delete Conversation\" button.");
        mtext3.setText("You may select a conversation that you had with a partner to delete.");
        mtext4.setText("Once you are done editing, click on the \"Done Editing\" button to go back to the previous page.");
        mtext5.setText("");
        mtext6.setText("");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b8(View view) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Help.this);
        final View mView = getLayoutInflater().inflate(R.layout.fragment_add_item, null);

        final TextView mTitle = mView.findViewById(R.id.title);
        final TextView mtext1 = mView.findViewById(R.id.step1);
        final TextView mtext2 = mView.findViewById(R.id.step2);
        final TextView mtext3 = mView.findViewById(R.id.step3);
        final TextView mtext4 = mView.findViewById(R.id.step4);
        final TextView mtext5 = mView.findViewById(R.id.step5);
        final TextView mtext6 = mView.findViewById(R.id.step6);
        final Button myBtn = mView.findViewById(R.id.confirm_add_button);

        mTitle.setText("What if I forgot my password?");
        mtext1.setText("If you have forgotten your password you may click on \"Reset Password\" on the login page.");
        mtext2.setText("Type in the email address associated with your account and click \"Submit\".");
        mtext3.setText("Your security question will be displayed below.");
        mtext4.setText("Enter in the answer to your security question.");
        mtext5.setText("An password reset email will be sent to your email address.");
        mtext6.setText("Click on the link in the email to reset your password.");


        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        myBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();


//
            }
        });
    }

    public void b9(View view) {
        startActivity(new Intent(Help.this, Main_Page2.class));
    }
}
