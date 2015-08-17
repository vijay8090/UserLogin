package com.vijay.userlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vijay.userlogin.customview.DateDisplayPicker;

public class Home extends AppCompatActivity implements View.OnClickListener {

    Button bLogout;
    EditText etEmail, etPassword, etConfirmPassword;
    DateDisplayPicker etDob;
    RadioGroup rgGender;
    UserLocalStore userLocalStore;
    RadioButton rbMale, rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etDob  = (DateDisplayPicker) findViewById(R.id.etDob);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        bLogout = (Button) findViewById(R.id.bLogout);
        bLogout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(authenticateUser()){
            displayDetails();
        } else {
            startActivity(new Intent(Home.this, Login.class));
        }
    }

    private boolean authenticateUser(){

        return userLocalStore.isUserLoggedIn();
    }

    private void displayDetails(){

        User loggedUser = userLocalStore.getLoggedInuser();
        etEmail.setText(loggedUser.getEmail1());

       /* etDob.onDateSet(new DatePicker(null), 2015, 6,
        4);*/
        etDob.setText(loggedUser.getDob());

        if("male".equalsIgnoreCase(loggedUser.getGender())){
            rbMale.setChecked(true);
            rbFemale.setChecked(false);
        }else {
            rbMale.setChecked(false);
            rbFemale.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bLogout :

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this, Login.class));

                break;
        }
    }
}
