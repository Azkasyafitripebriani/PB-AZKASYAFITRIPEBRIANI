package com.example.helloworld;

import static androidx.fragment.app.FragmentManager.TAG;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.helloworld.models.UserDetails;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    Button singUpBtn;
    TextInputEditText usernameSingUp, emailSingUp, passwordSingUp, nimPengguna;
    FirebaseAuth mAuth;

    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        singUpBtn = findViewById(R.id.SingUpbtn);
        usernameSingUp = findViewById(R.id.usernameSingUp);

        passwordSingUp = findViewById(R.id.PasswordSingUp);
        nimPengguna = findViewById(R.id.nimPengguna);

        singUpBtn.setOnClickListener(view ->{
            String username, email, password, NIM;

            username = String.valueOf(usernameSingUp.getText());
            email = String.valueOf(emailSingUp.getText());
            password = String.valueOf(passwordSingUp.getText());
            NIM = String.valueOf(nimPengguna.getText());

            if (TextUtils.isEmpty(username)){
                Toast.makeText(MainActivity2.this, "Enter Usename", Toast.LENGTH_LONG).show();
                usernameSingUp.requestFocus();
            }else if (TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity2.this, "Enter Email", Toast.LENGTH_LONG).show();
                emailSingUp.requestFocus();
            }else if (TextUtils.isEmpty(password)){
                Toast.makeText(MainActivity2.this, "Enter Password", Toast.LENGTH_LONG).show();
                passwordSingUp.requestFocus();
            }else if (TextUtils.isEmpty(NIM)) {
                Toast.makeText(MainActivity2.this, "Please Insert your NIM", Toast.LENGTH_LONG).show();
                nimPengguna.requestFocus();
            } else{
                //methoads public void
                registerUser(username, email, password, NIM);
            }
        });

    }

    private void registerUser(String username, String password, String NIM, String Email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser fuser = auth.getCurrentUser();
                if (fuser != null) {
                    String uid = fuser.getUid();

                    UserDetails userDetails = new UserDetails(uid, username, Email, password, NIM);

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                    reference.child(uid).setValue(userDetails).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            fuser.sendEmailVerification().addOnCompleteListener(task2 -> {
                                if (task2.isSuccessful()) {
                                    Toast.makeText(MainActivity2.this, "Verifikasi Email Telah Dikirim", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity2.this, "Verifikasi Email Gagal Dikirim", Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "Register Error");
                                }
                            });
                        } else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                emailSingUp.setError("Email is Already registered");
                            } catch (Exception e) {
                                //
                                Log.e(TAG, e.getMessage());
                                Toast.makeText(MainActivity2.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

