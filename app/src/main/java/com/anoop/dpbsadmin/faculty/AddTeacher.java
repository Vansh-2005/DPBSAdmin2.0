package com.anoop.dpbsadmin.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.anoop.dpbsadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddTeacher extends AppCompatActivity {

    private ImageView addTeacerImage;
    private EditText addTeacherName,addTeacherEmail,addTeacherPost;
    private Spinner addTeacherCategory;
    private Button addTeacherbtn;
    private final int REQ =1;
    private Bitmap bitmap = null;
    private String category;
    //for firebase
    private String name,email,post,downloadUrl = "";
    private ProgressDialog pd;
    private StorageReference storageReference;
    private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        addTeacerImage= findViewById(R.id.addTeacerImage);
        addTeacherName = findViewById(R.id.addTeacherName);
        addTeacherEmail= findViewById(R.id.addTeacherEmail);
        addTeacherPost = findViewById(R.id.addTeacherPost);
        addTeacherCategory = findViewById(R.id.addTeacherCategory);
        addTeacherbtn = findViewById(R.id.addTeacherbtn);
        pd = new ProgressDialog(this);

        reference = FirebaseDatabase.getInstance().getReference().child("teacher");
        storageReference = FirebaseStorage.getInstance().getReference();

        String[] items = new String[]{"Select Category","B.C.A Department","B.Com Department","Chemistry Department(M.Sc.)"
                ,"Department Of Education","Department Of Physics(P.G)","Department Of Chemistry(P.G)","Department Of Mathematics"
        ,"Department Of Statistics","Department Of Political Science","Department Of Physical Education","Department Of Sanskrit(P.G)"
        ,"Department Of Economics","Department Of Sociology","Department Of Hindi","Department Of English"};
        addTeacherCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,items));

        addTeacherCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = addTeacherCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addTeacerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        addTeacherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

    }

    private void checkValidation() {
        name = addTeacherName.getText().toString();
        email = addTeacherEmail.getText().toString();
        post = addTeacherPost.getText().toString();

        if (name.isEmpty()){
            addTeacherName.setError("Empty");
            addTeacherName.requestFocus();
        }
        else if (email.isEmpty()){
            addTeacherEmail.setError("Empty");
            addTeacherEmail.requestFocus();
        }
        else if(post.isEmpty()){
            addTeacherPost.setError("Empty");
            addTeacherPost.requestFocus();
        }
        else if(category.equals("Select Category")){
            Toast.makeText(this, "Please provide category", Toast.LENGTH_SHORT).show();
        }
        else if (bitmap==null){
            pd.setMessage("Uploading...");
            pd.show();
            insertData();
        }
        else{
            pd.setMessage("Uploading...");
            pd.show();
            uploadImage();
        }
    }

    private void uploadImage() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);

        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child("Teachers").child(finalimg+"jpg");
        final UploadTask uploadTask = filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(AddTeacher.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                if(task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    insertData();

                                }
                            });
                        }
                    });
                }
                else{
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void insertData() {
        if (category == "B.C.A Department") {
            dbRef = reference.child("BCA Department");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (category == "B.Com Department"){
            dbRef = reference.child("BCom Department");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if(category == "Chemistry Department(M.Sc.)")
        {
            dbRef = reference.child("Chemistry Department");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if(category == "Department Of Education")
        {
            dbRef = reference.child("Department Of Education");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if(category == "Department Of Physics(P.G)")
        {
            dbRef = reference.child("Department Of Physics");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Chemistry(P.G)")
        {
            dbRef = reference.child("Department Of Chemistry");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Mathematics")
        {
            dbRef = reference.child("Department Of Mathematics");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Statistics")
        {
            dbRef = reference.child("Department Of Statistics");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Political Science")
        {
            dbRef = reference.child("Department Of Political Science");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Physical Education")
        {
            dbRef = reference.child("Department Of Physical Education");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Sanskrit(P.G)")
        {
            dbRef = reference.child("Department Of Sanskrit");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Economics")
        {
            dbRef = reference.child("Department Of Economics");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Sociology")
        {
            dbRef = reference.child("Department Of Sociology");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }
        else if(category == "Department Of Hindi")
        {
            dbRef = reference.child("Department Of Hindi");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

 else if(category == "Department Of English")
        {
            dbRef = reference.child("Department Of English");
            final String uniquekey = dbRef.push().getKey();

            TeacherData teacherData = new TeacherData(name, email, post, downloadUrl, uniquekey);
            dbRef.child(uniquekey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Teacher Added Succesfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(AddTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }


        else{
            Toast.makeText(this, "Category not added till now", Toast.LENGTH_SHORT).show();
        }

    }

    private void openGallery() {
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            addTeacerImage.setImageBitmap(bitmap);
        }
    }


}