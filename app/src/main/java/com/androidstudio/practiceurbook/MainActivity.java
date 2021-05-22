package com.androidstudio.practiceurbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MemberInitActivity";
    private DatabaseReference mPostReference;
    /*private EditText email;
    private EditText password;
    private EditText name,phone;
    private EditText univ,campus,faculty;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        // Initialize Firebase Auth
        if (user == null) { //회원정보가 없을때
            MystartActivity(SignUpActivity.class);
        } else {
            //회원가입했거나 로그인했을때
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid()); //Uid로 찾음
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                                if(document.getData()==null){
                                    MystartActivity(MemberinitActivity.class);
                                }

                            } else {
                                Log.d(TAG, "No such document");
                            }
                        }

                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });

        }

        findViewById(R.id.logoutbutton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v. getId()){
                case R.id.logoutbutton:
                    FirebaseAuth.getInstance().signOut();
                    MystartActivity(SignUpActivity.class);
                    break;
            }
        }
    };

    private void MystartActivity(Class c) {                                                          //c액티비티로 연결
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}