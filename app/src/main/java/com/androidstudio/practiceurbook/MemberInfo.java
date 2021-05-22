package com.androidstudio.practiceurbook;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

import static android.os.Build.ID;

@IgnoreExtraProperties
public class MemberInfo {
    private DatabaseReference mPostReference;
        public String id;
        public String name;
        public String phone;
        public String univ;
        public String campus;
        public String faculty;

        public MemberInfo(String id, String name, String phone, String univ, String campus, String faculty) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.univ = univ;
            this.campus = campus;
            this.faculty = faculty;
        }

        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getId(){
            return this.id;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getPhone(){
            return this.phone;
        }
        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getUniv(){
            return this.univ;
        }
        public void setUniv(String univ){
            this.univ = univ;
        }
        public String getCampus(){
            return this.campus;
        }
        public void setCampus(String campus){
            this.campus = campus;
        }
        public String getFaculty(){
            return this.faculty;
        }
        public void setFaculty(String faculty){
            this.faculty = faculty;
        }


}


