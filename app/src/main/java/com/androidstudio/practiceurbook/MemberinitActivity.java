package com.androidstudio.practiceurbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;


public class MemberinitActivity extends AppCompatActivity {
    private static final String TAG = "MemberInitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);
// Initialize Firebase Auth

        findViewById(R.id.checkbutton).setOnClickListener(onClickListener);
                    //생성한 버튼들을 연결
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.checkbutton:
                    profileUpdate();
                    Log.e("클릭","클릭");
                    break;

            }
        }
    };

    private void profileUpdate() {
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String phone = ((EditText)findViewById(R.id.phoneEditText)).getText().toString();
        String univ = ((EditText)findViewById(R.id.univEditText)).getText().toString();
        String campus = ((EditText)findViewById(R.id.campusEditText)).getText().toString();
        String faculty = ((EditText)findViewById(R.id.facultyEditText)).getText().toString();



        if(email.length() > 0 && name.length() > 0 && phone.length() > 0 && univ.length()> 0 && campus.length() > 0 && faculty.length() > 0 ){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            MemberInfo memberInfo = new MemberInfo(name, email, phone, univ, campus, faculty);

            if(user != null){
                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("회원정보 등록을 성공하였습니다.");
                                MystartActivity(MainActivity.class);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            startToast("회원정보 등록에 실패하였습니다.");
                        }
                    });


            }


        }else{
            startToast("회원정보를 입력해 주세요.");
        }




    }
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    private void MystartActivity(Class c) {                                                             //c액티비티로 연결
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}

