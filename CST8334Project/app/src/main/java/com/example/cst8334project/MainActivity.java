package com.example.cst8334project;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //log.i("This is a test");
        Log.i("MainActivity","Another test");

//        try {
//            startAc();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        makeFile();
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



//        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"MyDir");
//        if(!fileDir.exists()){
//            try{
//                fileDir.mkdir();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            //Toast.makeText(this, "fileDir exists",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this, "fileDir doesn't exist",Toast.LENGTH_SHORT).show();
//        }
//        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator +"BlogData"+File.separator+"MyText.txt");
//        if(!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Toast.makeText(this, "file doesn't exists",Toast.LENGTH_LONG).show();
//        } else {
//           // Toast.makeText(this, "file exist",Toast.LENGTH_SHORT).show();
//        }
    }

    String columnString = "\"PersonName\",\"Gender\",\"Street1\",\"postOffice\",\"Age\"";
    //String dataString   =   "\"" + currentUser.userName +"\",\"" + currentUser.gender + "\",\"" + currentUser.street1 + "\",\"" + currentUser.postOFfice.toString()+ "\",\"" + currentUser.age.toString() + "\"";
    String dataString = "\"" + "Dash Xu" + "\",\"" + "female" + "\",\"" + "Baseline" + "\",\"" + "POO" + "\",\"" + "21" + "\"";
    String combinedString = columnString + "\n" + dataString;

//    public void makeFile(){
//        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
//        String fileName = "AnalysisData.csv";
//        String filePath = baseDir + File.separator + fileName;
//        File f = new File(filePath );
//        CSVWriter writer;
//// File exist
//        if(f.exists() && !f.isDirectory()){
//            mFileWriter = new FileWriter(filePath , true);
//            writer = new CSVWriter(mFileWriter);
//        }
//        else {
//            writer = new CSVWriter(new FileWriter(filePath));
//        }
//        String[] data = {"Ship Name","Scientist Name", "...",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").formatter.format(date)});
//
//        writer.writeNext(data);
//
//        writer.close();
//    }

    protected void startAc() throws IOException {
        File file;

        File root = Environment.getExternalStorageDirectory();
        if (true) {

            File dir = new File(root.getAbsolutePath() + "/per");
            if ( !dir.exists() )
            {
                dir.createNewFile();         // This line will create new blank line.
            }

//            File dir = new File(root.getAbsolutePath() + "/PersonData");
            dir.mkdirs();
            Log.i("MainActivity","direct has been created");
            file = new File(dir, "/Data.csv");
            Log.i("MainActivity","new file has been created");
            FileOutputStream out = null;
            if(file.exists() && file.isDirectory()) {

                try {
                    out = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    out.write(combinedString.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                Log.i("MainActivity","file doesn't exist");
            }


            Uri u1 = null;
            u1 =Uri.fromFile(file);
            Log.i("MainActivity","the file path is "+u1.toString());
            Toast.makeText(this, "SAVED",Toast.LENGTH_SHORT).show();
//
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Person Details");
            sendIntent.putExtra(Intent.EXTRA_STREAM,u1);
            sendIntent.setType("text/html");

            startActivity(sendIntent);
        }
else {
            Toast.makeText(this, "root can't write",Toast.LENGTH_SHORT).show();
        }
//

    }
}
