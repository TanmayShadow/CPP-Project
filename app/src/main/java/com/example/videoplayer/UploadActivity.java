package com.example.videoplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class UploadActivity extends AppCompatActivity {
    ArrayAdapter<String> a;
    String str[] = {"None","Video","Image","Audio"};
    String type="";
    Spinner content_type;
    EditText name,description;
    ImageView i1;
    Uri imageUri,videoUri,originalImage;
    Bitmap bitmapImage;
    int width,height,id;
    StorageReference storageReference,originalReference;
    SQLiteDatabaseClass sql;
    ProgressDialog progressDialog;
    BackgroundMYSQL backgroundMYSQL;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        content_type=findViewById(R.id.content_type);
        name=findViewById(R.id.content_name);
        description=findViewById(R.id.description);
        i1=findViewById(R.id.i1);
        backgroundMYSQL = new BackgroundMYSQL(this);
        sql=new SQLiteDatabaseClass(this);
        id=sql.getLogin();

        a = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,str);
        content_type.setAdapter(a);
        content_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (str[i])
                {
                    case "Video":
                        type="video";
                        getVideo();
                        break;
                    case "Image":
                        type="image";
                        getImage();
                        break;
                    case "Audio":
                        type="audio";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type="";
            }
        });
    }

    public void upload(View view) {
        if(id!=-1)
        {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading File...");
            progressDialog.show();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
            Date now = new Date();
            String cname=name.getText().toString();
            String cdescription=description.getText().toString();
            if(cname.equals(""))
            {
                Toast.makeText(this, "Please enter valid name for the content", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String filename = id+"_"+formatter.format(now)+".png";

                if(type.equals("image"))
                {
                    storageReference= FirebaseStorage.getInstance().getReference("images/"+filename);
                    storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            backgroundMYSQL.execute("upload", id+"",type,cname,cdescription,filename);
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    });
//                    originalReference = FirebaseStorage.getInstance().getReference("images/"+filename);
//                    originalReference.putFile(originalImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            backgroundMYSQL.execute("upload", id+"",type,cname,cdescription,filename);
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Toast.makeText(UploadActivity.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Toast.makeText(UploadActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
                else if(type.equals("video"))
                {
                    storageReference= FirebaseStorage.getInstance().getReference("videos/"+filename);
                    storageReference.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            backgroundMYSQL.execute("upload", id+"",type,cname,cdescription,filename);
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(UploadActivity.this, "Video Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Toast.makeText(UploadActivity.this, "Failed to upload video", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }
        else
        {
            Toast.makeText(this, "Login First", Toast.LENGTH_SHORT).show();
        }
        
    }

    //getting image
    public void getImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    public void getVideo()
    {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,101);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        content_type.setSelection(0);
        if(requestCode==100 && data!=null && data.getData()!=null)
        {
            originalImage=data.getData();
            imageUri = data.getData();
            i1.setImageURI(imageUri);
//            bitmapImage = ((BitmapDrawable)i1.getDrawable()).getBitmap();
//            bitmapImage = bitmapImage.copy( Bitmap.Config.ARGB_8888 , true);
//
//            width=bitmapImage.getWidth();
//            height=bitmapImage.getHeight();
//            Toast.makeText(this, "Width"+bitmapImage.getWidth(), Toast.LENGTH_SHORT).show();
//
//            int pix[][]=new int[width][height];
//            int keys[][]=new int[width][height];
//            int key=-97,temp=1;
//            try{
//                for (int y = 0; y < height; y++)
//                {
//                    for (int x = 0; x < width; x++)
//                    {
//                        //Retrieving contents of a pixel
//                        int pixel = bitmapImage.getPixel(x,y);
//                        pix[x][y]=pixel;
//                    }
//                }
//                //getting all keys
//                for (int y = 0; y < height; y++)
//                {
//                    for (int x = 0; x < width; x++)
//                    {
//                        //Setting pixel
//                        int m=(int)((Math.pow(2,32))-1);
//                        temp=((key*temp+7)/3)%m;
//                        keys[x][y]=temp;
//                    }
//                }
//
//                //Encrypting Image
//                for (int y = 0; y < height; y++)
//                {
//                    for (int x = 0; x < width; x++)
//                    {
//                        //Setting pixel
//                        pix[x][y] = pix[x][y]^keys[x][y];
//                        if(x%2==0)
//                        {
//                            pix[x][y]=pix[x][y]*-1;
//                        }
//
//                        bitmapImage.setPixel(x,y,pix[x][y]);
//                    }
//                }
//                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//                bitmapImage.compress(Bitmap.CompressFormat.PNG,100,bytes);
//                i1.setImageBitmap(bitmapImage);
//                int no = (int)Math.random()*100;
//                String path1 = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),bitmapImage,"myEncImg.png",null);
//                imageUri = Uri.parse(path1);
//                i1.setImageURI(imageUri);
//                i1.setImageBitmap(bitmapImage);
//            }
//            catch (Exception e)
//            {
//                Log.d("EncryptImage:",""+e);
//            }
        }
        else if(requestCode==101 && data!=null && data.getData()!=null)
        {
            videoUri=data.getData();
        }
    }


}