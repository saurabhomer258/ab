package com.example.saurabhomer.bornbhukkad;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.saurabhomer.bornbhukkad.Model.User;
import com.example.saurabhomer.bornbhukkad.common.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {

    EditText edtPhone,edtPassword;
    FButton btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        edtPassword =(EditText)findViewById(R.id.edtPassword);
        edtPhone =(EditText)findViewById(R.id.edtPhone);
        btnSignIn =(FButton) findViewById(R.id.btnSignIn);


        //Init Firebase
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Common.isConnectedToInterner(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Please waiting");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //Check if User not Exist in the database
                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                                //Get User Information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);

                                //set Phone

                                user.setPhone(edtPhone.getText().toString());
                                if (user.getPassword().equals(edtPassword.getText().toString())) {
                                    Intent homeIntent = new Intent(SignIn.this, Home.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);
                                    finish();


                                    Toast.makeText(SignIn.this, "SignIn Sucessfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignIn.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "User not exist", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(SignIn.this, "Please Check Your Connection!!!!", Toast.LENGTH_SHORT).show();
                    return ;
                }
            }

        });

    }
}