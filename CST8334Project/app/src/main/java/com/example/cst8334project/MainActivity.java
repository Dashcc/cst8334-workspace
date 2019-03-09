package com.example.cst8334project;

import android.app.Activity;
import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.os.Bundle;

import com.example.cst8334project.authentication.NewPasswordCheckerAsyncTask;
import com.example.cst8334project.emailservice.UnsentEmailCheckerAsyncTask;

import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    protected static final String ACTIVITY_NAME = "MainActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent,50);

            }
        });

 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        createFile();


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "permission granted",Toast.LENGTH_SHORT).show();
                }
        }
        // Log.i("MainActivity","Another test");


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==50){
            Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
        }
        if(resultCode == Activity.RESULT_OK) {
            Log.i(ACTIVITY_NAME, "Returned Message is Result Ok");
        }
        String messagePassed = data.getStringExtra("Response");
        Toast toast = Toast.makeText(this, messagePassed, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    public void createFile(){

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"File.csv");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(combinedString.getBytes());
            fos.close();
            Toast.makeText(this, "saved file",Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "file not found",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "error saving",Toast.LENGTH_SHORT).show();
        }


    }

    String columnString = "\"PersonName\",\"Gender\",\"Street1\",\"postOffice\",\"Age\"";
    //String dataString   =   "\"" + currentUser.userName +"\",\"" + currentUser.gender + "\",\"" + currentUser.street1 + "\",\"" + currentUser.postOFfice.toString()+ "\",\"" + currentUser.age.toString() + "\"";
    String dataString = "\"" + "Dash Xu" + "\",\"" + "female" + "\",\"" + "Baseline station" + "\",\"" + "POst" + "\",\"" + "21" + "\"";
    String combinedString = columnString + "\n" + dataString;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

}