package com.anoop.dpbsadmin.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anoop.dpbsadmin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UpdateFaculty extends AppCompatActivity {

    FloatingActionButton fab;
    private RecyclerView bcaDepartment,bcomDepartment,CmscDepartment,doeDepartment,dopDepartment,docDepartment,domDepartment
            ,dosDepartment,dopsDepartment,dopeDepartment,dospgDepartment,DoeDepartment,DosDepartment,dohDepartment,doenDepartment;

    private LinearLayout bcaNoData,bcomNoData,CmscNoData,doeNoData,dopNoData,docNoData,domNoData,dosNoData,dopsNoData
            ,dopeNoData,dospgNoData,DoeNoData,DosNoData,dohNoData,doenNoData;
    private List<TeacherData> list1, list2,list3,list4,list5,list6,list7,list8,list9,list10,list11,list12,list13,list14,list15;

    private DatabaseReference reference,dbRef;
    private TeacherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_faculty);
        //setting up the floating button
        fab = findViewById(R.id.fab);

        bcaNoData= findViewById(R.id.bcaNoData);
        bcomNoData= findViewById(R.id.bcomNoData);
        CmscNoData= findViewById(R.id.CmscNoData);
        doeNoData= findViewById(R.id.doeNoData);
        dopNoData= findViewById(R.id.dopNoData);
        docNoData= findViewById(R.id.docNoData);
        domNoData= findViewById(R.id.domNoData);
        dosNoData= findViewById(R.id.dosNoData);
        dopsNoData= findViewById(R.id.dopsNoData);
        dopeNoData= findViewById(R.id.dopeNoData);
        dospgNoData= findViewById(R.id.dospgNoData);
        DoeNoData= findViewById(R.id.DoeNoData);
        DosNoData= findViewById(R.id.DosNoData);
        dohNoData= findViewById(R.id.dohNoData);
        doenNoData= findViewById(R.id.doenNoData);

        bcaDepartment= findViewById(R.id.bcaDepartment);
        bcomDepartment= findViewById(R.id.bcomDepartment);
        CmscDepartment= findViewById(R.id.CmscDepartment);
        doeDepartment= findViewById(R.id.doeDepartment);
        dopDepartment= findViewById(R.id.dopDepartment);
        docDepartment= findViewById(R.id.docDepartment);
        domDepartment= findViewById(R.id.domDepartment);
        dosDepartment= findViewById(R.id.dosDepartment);
        dopsDepartment= findViewById(R.id.dopsDepartment);
        dopeDepartment= findViewById(R.id.dopeDepartment);
        dospgDepartment= findViewById(R.id.dospgDepartment);
        DoeDepartment= findViewById(R.id.DoeDepartment);
        DosDepartment= findViewById(R.id.DosDepartment);
        dohDepartment= findViewById(R.id.dohDepartment);
        doenDepartment= findViewById(R.id.doenDepartment);

        reference= FirebaseDatabase.getInstance().getReference().child("teacher");
        bcaDepartment();
        bcomDepartment();
        CmscDepartment();
        doeDepartment();
        dopDepartment();
        docDepartment();
        domDepartment();
        dosDepartment();
        dopsDepartment();
        dopeDepartment();
        dospgDepartment();
        DoeDepartment();
        DosDepartment();
        dohDepartment();
        doenDepartment();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateFaculty.this,AddTeacher.class));
            }
        });

    }

    private void bcaDepartment() {

        dbRef = reference.child("BCA Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    bcaNoData.setVisibility(View.VISIBLE);
                    bcaDepartment.setVisibility(View.GONE);
                }
                else {
                    bcaNoData.setVisibility(View.GONE);
                    bcaDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    bcaDepartment.setHasFixedSize(true);
                    bcaDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list1,UpdateFaculty.this,"BCA Department");
                    bcaDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void bcomDepartment() {

        dbRef = reference.child("BCom Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    bcomNoData.setVisibility(View.VISIBLE);
                    bcomDepartment.setVisibility(View.GONE);
                }
                else {
                    bcomNoData.setVisibility(View.GONE);
                    bcomDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    bcomDepartment.setHasFixedSize(true);
                    bcomDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list2,UpdateFaculty.this,"BCom Department");
                    bcomDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void CmscDepartment() {

        dbRef = reference.child("Chemistry Department");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    CmscNoData.setVisibility(View.VISIBLE);
                    CmscDepartment.setVisibility(View.GONE);
                }
                else {
                    CmscNoData.setVisibility(View.GONE);
                    CmscDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    CmscDepartment.setHasFixedSize(true);
                    CmscDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list3,UpdateFaculty.this,"Chemistry Department");
                    CmscDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void doeDepartment() {

        dbRef = reference.child("Department Of Education");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    doeNoData.setVisibility(View.VISIBLE);
                    doeDepartment.setVisibility(View.GONE);
                }
                else {
                    doeNoData.setVisibility(View.GONE);
                    doeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    doeDepartment.setHasFixedSize(true);
                    doeDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list4,UpdateFaculty.this,"Department Of Education");
                    doeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dopDepartment() {

        dbRef = reference.child("Department Of Physics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list5= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dopNoData.setVisibility(View.VISIBLE);
                    dopDepartment.setVisibility(View.GONE);
                }
                else {
                    dopNoData.setVisibility(View.GONE);
                    dopDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list5.add(data);
                    }
                    dopDepartment.setHasFixedSize(true);
                    dopDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list5,UpdateFaculty.this,"Department Of Physics");
                    dopDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void docDepartment() {

        dbRef = reference.child("Department Of Chemistry");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list6= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    docNoData.setVisibility(View.VISIBLE);
                    docDepartment.setVisibility(View.GONE);
                }
                else {
                    docNoData.setVisibility(View.GONE);
                    docDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list6.add(data);
                    }
                    docDepartment.setHasFixedSize(true);
                    docDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list6,UpdateFaculty.this,"Department Of Chemistry");
                    docDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void domDepartment() {

        dbRef = reference.child("Department Of Mathematics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list7= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    domNoData.setVisibility(View.VISIBLE);
                    domDepartment.setVisibility(View.GONE);
                }
                else {
                    domNoData.setVisibility(View.GONE);
                    domDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list7.add(data);
                    }
                    domDepartment.setHasFixedSize(true);
                    domDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list7,UpdateFaculty.this,"Department Of Mathematics");
                    domDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dosDepartment() {

        dbRef = reference.child("Department Of Statistics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list8= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dosNoData.setVisibility(View.VISIBLE);
                    dosDepartment.setVisibility(View.GONE);
                }
                else {
                    dosNoData.setVisibility(View.GONE);
                    dosDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list8.add(data);
                    }
                    dosDepartment.setHasFixedSize(true);
                    dosDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list8,UpdateFaculty.this,"Department Of Statistics");
                    dosDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dopsDepartment() {

        dbRef = reference.child("Department Of Political Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list9= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dopsNoData.setVisibility(View.VISIBLE);
                    dopsDepartment.setVisibility(View.GONE);
                }
                else {
                    dopsNoData.setVisibility(View.GONE);
                    dopsDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list9.add(data);
                    }
                    dopsDepartment.setHasFixedSize(true);
                    dopsDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list9,UpdateFaculty.this,"Department Of Political Science");
                    dopsDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dopeDepartment() {

        dbRef = reference.child("Department Of Physical Education");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list10= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dopeNoData.setVisibility(View.VISIBLE);
                    dopeDepartment.setVisibility(View.GONE);
                }
                else {
                    dopeNoData.setVisibility(View.GONE);
                    dopeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list10.add(data);
                    }
                    dopeDepartment.setHasFixedSize(true);
                    dopeDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list10,UpdateFaculty.this,"Department Of Physical Education");
                    dopeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dospgDepartment() {

        dbRef = reference.child("Department Of Sanskrit");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list11= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dospgNoData.setVisibility(View.VISIBLE);
                    dospgDepartment.setVisibility(View.GONE);
                }
                else {
                    dospgNoData.setVisibility(View.GONE);
                    dospgDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list11.add(data);
                    }
                    dospgDepartment.setHasFixedSize(true);
                    dospgDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list11,UpdateFaculty.this,"Department Of Sanskrit");
                    dospgDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void DoeDepartment() {

        dbRef = reference.child("Department Of Economics");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list12= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    DoeNoData.setVisibility(View.VISIBLE);
                    DoeDepartment.setVisibility(View.GONE);
                }
                else {
                    DoeNoData.setVisibility(View.GONE);
                    DoeDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list12.add(data);
                    }
                    DoeDepartment.setHasFixedSize(true);
                    DoeDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list12,UpdateFaculty.this,"Department Of Economics");
                    DoeDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void DosDepartment() {

        dbRef = reference.child("Department Of Sociology");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list13= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    DosNoData.setVisibility(View.VISIBLE);
                    DosDepartment.setVisibility(View.GONE);
                }
                else {
                    DosNoData.setVisibility(View.GONE);
                    DosDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list13.add(data);
                    }
                    DosDepartment.setHasFixedSize(true);
                    DosDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list13,UpdateFaculty.this,"Department Of Sociology");
                    DosDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void dohDepartment() {

        dbRef = reference.child("Department Of Hindi");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list14= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    dohNoData.setVisibility(View.VISIBLE);
                    dohDepartment.setVisibility(View.GONE);
                }
                else {
                    dohNoData.setVisibility(View.GONE);
                    dohDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list14.add(data);
                    }
                    dohDepartment.setHasFixedSize(true);
                    dohDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list14,UpdateFaculty.this,"Department Of Hindi");
                    dohDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    } private void doenDepartment() {

        dbRef = reference.child("Department Of English");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list15= new ArrayList<>();
                if(!dataSnapshot.exists()){
                    doenNoData.setVisibility(View.VISIBLE);
                    doenDepartment.setVisibility(View.GONE);
                }
                else {
                    doenNoData.setVisibility(View.GONE);
                    doenDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list15.add(data);
                    }
                    doenDepartment.setHasFixedSize(true);
                    doenDepartment.setLayoutManager(new LinearLayoutManager(UpdateFaculty.this));
                    adapter= new TeacherAdapter(list15,UpdateFaculty.this,"Department Of English");
                    doenDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(UpdateFaculty.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



}