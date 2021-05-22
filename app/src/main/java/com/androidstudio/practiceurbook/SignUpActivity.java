package com.androidstudio.practiceurbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Dictionary;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;                                                                     //인스턴스선언

    /*private EditText email;
    private EditText password;
    private EditText univ,campus;
    private EditText phone;
    private EditText name;
    private EditText faculty;
    private Button btn;*/
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        /*univ = (EditText) findViewById(R.id.univedittext);
        campus = (EditText) findViewById(R.id.campusedittext);
        email = (EditText) findViewById(R.id.emailedittext);
        phone=(EditText)findViewById(R.id.phoneedittext);
        name=(EditText)findViewById(R.id.nameedittext);
        password=(EditText)findViewById(R.id.passwordedittext);
        faculty=(EditText)findViewById(R.id.facultyedittext);
        btn = (Button) findViewById(R.id.signupbutton);*/

// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.checkbutton).setOnClickListener(onClickListener);
        findViewById(R.id.gotologinbutton).setOnClickListener(onClickListener);



    }




    @Override
    public void onBackPressed() {                                                                   //뒤로가기버튼
        super.onBackPressed();
        moveTaskToBack(true);                                                              // 태스크를 백그라운드로 이동
        android.os.Process.killProcess(android.os.Process.myPid());                                 //앱종료&나가기
        System.exit(1);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {                            //->view 에 접근해서 할 것
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkbutton:                                                             //회원가입버튼누를시
                    SignUp();                                                                       //회원가입실행
                    Log.e("클릭", "클릭");                                                //클릭시 클릭했다는 logcat
                    break;
                case R.id.gotologinbutton:                                                          //로그인버튼누를시
                    MystartActivity(LoginActivity.class);                                                           //로그인창으로이동
                    break;

            }
        }
    };

    private void SignUp() {
        String email = ((EditText) findViewById(R.id.emailedittext)).getText().toString();          //각 변수에 문자열 저장
        //String name = ((EditText) findViewById(R.id.nameedittext)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordedittext)).getText().toString();
        String passwordCheck = ((EditText) findViewById(R.id.passwordcheckedittext)).getText().toString();
        /*String phone =  ((EditText) findViewById(R.id.phoneedittext)).getText().toString();
        String univ =  ((EditText) findViewById(R.id.univedittext)).getText().toString();
        String campus =  ((EditText) findViewById(R.id.campusedittext)).getText().toString();
        String faculty =  ((EditText) findViewById(R.id.facultyedittext)).getText().toString();*/


        if (email.length() > 0 && password.length() > 0 ) {
            if (password.equals(passwordCheck)) {   //if,비밀번호=비밀번호확인

                mAuth.createUserWithEmailAndPassword(email, password)                               //email,password가 firebaseAuthentication에 저장
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //FirebasePost (ID, name, phone, univ, campus, faculty);or postFirebaseDatabase(true)
                                    startToast("회원가입이 성공했습니다.");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    MystartActivity(LoginActivity.class);

                                } else {  //실패
                                    // If sign in fails, display a message to the user.
                                    if (task.getException() != null) {   //입력값!= null
                                        startToast(task.getException().toString());                 //toString()=자신이가진값 그대로 리턴

                                    }


                                }

                                // ...
                            }
                        });

            } else {
                startToast("비밀번호가 일치하지 않습니다.");
            }

        } else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }


    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();                               //메세지띄우기
    }


    private void MystartActivity(Class c) {                                                             //c액티비티로 연결
        Intent intent = new Intent(this, c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}

/*class User {
    public String name;
    public String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Dictionary<String, Object> ToDictionary() {
        Dictionary<String, Object> result = new Dictionary<String, Object>();
        result["name"] = name;
        result["email"] = email;

        return result;
    }
}*/

