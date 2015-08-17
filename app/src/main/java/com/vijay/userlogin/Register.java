package com.vijay.userlogin;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vijay.userlogin.customview.DateDisplayPicker;

public class Register extends AppCompatActivity implements View.OnClickListener {


    Button bRegister;
    EditText etEmail, etPassword, etConfirmPassword;
    DateDisplayPicker etDob;
    RadioGroup rgGender;
   // RadioButton rbMale, rbFemale;
    RadioButton rbSelectedGender;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etDob  = (DateDisplayPicker) findViewById(R.id.etDob);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);

        //rbMale = (RadioButton) findViewById(R.id.rbMale);
        //rbFemale = (RadioButton) findViewById(R.id.rbFemale);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }


    private boolean isValidate( User registeredUser){

        if(registeredUser.getEmail1() == null || registeredUser.getEmail1().isEmpty()){
            Toast.makeText(Register.this,
                    "Email should not be blank", Toast.LENGTH_SHORT).show();

            return false;
        }

        if(registeredUser.getPassword() == null || registeredUser.getPassword().isEmpty()){
            Toast.makeText(Register.this,
                    "Password should not be blank", Toast.LENGTH_SHORT).show();

            return false;
        }

        if(registeredUser.getConfirmPassword() == null || registeredUser.getConfirmPassword().isEmpty()){
            Toast.makeText(Register.this,
                    "Confirm password should not be blank", Toast.LENGTH_SHORT).show();

            return false;
        }



        if(registeredUser.getGender() == null || registeredUser.getGender().isEmpty()){
            Toast.makeText(Register.this,
                    "Gender should not be blank", Toast.LENGTH_SHORT).show();

            return false;
        }

        if(registeredUser.getDob() == null || registeredUser.getDob().isEmpty()){
            Toast.makeText(Register.this,
                    "DOB should not be blank", Toast.LENGTH_SHORT).show();

            return false;
        }


        if(!registeredUser.getPassword().equals(registeredUser.getConfirmPassword())){
            Toast.makeText(Register.this,
                    "Password and Confirm password should match", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bRegister:

                String email1 = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();
                String dob = etDob.getText().toString();
                String gender = null;
                int selectedId = rgGender.getCheckedRadioButtonId();



                // find the radiobutton by returned id
                rbSelectedGender = (RadioButton) findViewById(selectedId);

                //rbSelectedGender.setc

                if(rbSelectedGender != null){
                    gender =  rbSelectedGender.getText().toString();
                }



                User registeredUser = new User();
                registeredUser.setEmail1(email1);
                registeredUser.setPassword(password);
                registeredUser.setConfirmPassword(confirmPassword);
                registeredUser.setDob(dob);
                registeredUser.setGender(gender);

                if(isValidate(registeredUser)) {

                    register( registeredUser);

                }

                break;

        }
    }

    private void register(User registeredUser) {
        ServerRequests serverRequests = new ServerRequests(this);

        serverRequests.storeUserDataInBackground(registeredUser, new GetUserCallback() {
            @Override
            public void done(boolean result, User returnedUser) {
                if (!result) {
                    showErrorMessage();
                } else {
                  //  userLocalStore.storeUserData(registeredUser);
                  //  userLocalStore.setUserLoggedIn(true);
                    startActivity(new Intent(Register.this, Login.class));
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Register.this);
        dialogBuilder.setMessage("Error During registration");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();

    }

}
