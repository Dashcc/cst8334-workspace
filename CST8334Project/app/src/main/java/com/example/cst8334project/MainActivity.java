package com.example.cst8334project;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //log.i("This is a test");
        Log.i("MainActivity","Another test");

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

}
