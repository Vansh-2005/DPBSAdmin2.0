package com.anoop.dpbsadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.anoop.dpbsadmin.faculty.UpdateFaculty;
import com.anoop.dpbsadmin.notice.DeleteNoticeActivity;
import com.anoop.dpbsadmin.notice.UploadNotice;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Directing the cardview button to this activity by onclicklistener
    CardView uploadNotice,addGallaryImage,addEbook,addFaculty,deleteNotice,logout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences =this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("islogin","false").equals("false")){
            openLogin();
        }

        uploadNotice = findViewById(R.id.addnotice);
        addGallaryImage= findViewById(R.id.addGallaryImage);
        addEbook = findViewById(R.id.addEbook);
        addFaculty = findViewById(R.id.addFaculty);
        deleteNotice = findViewById(R.id.deleteNotice);
        logout = findViewById(R.id.logout);

        uploadNotice.setOnClickListener(this);
        addGallaryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        addFaculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void openLogin() {

        Toast.makeText(this, "Logout Succesfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.addnotice:
                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;

            case R.id.addGallaryImage:
                intent = new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;

            case R.id.addEbook:
                intent = new Intent(MainActivity.this,UploadpdfActivity.class);
                startActivity(intent);
                break;
            case R.id.addFaculty:
                intent = new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;

            case R.id.deleteNotice:
                intent = new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;

            case R.id.logout:
                editor.putString("islogin","false");
                editor.commit();
                openLogin();
                break;
        }

    }
}